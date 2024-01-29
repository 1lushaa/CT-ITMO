package expression.exceptions;

import expression.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import static java.util.Map.entry;
import java.util.Objects;


public final class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(String expression) throws InvalidExpressionException {
        return new Parser(new StringSource(expression)).parse();
    }

    private static class Parser extends AbstractParser {
        private final LinkedList<ToMiniStringExpression> stack;
        private final ArrayList<ToMiniStringExpression> notation;
        private static final Map<Character, ToMiniStringExpression> converter
                = Map.ofEntries(entry('+', new CheckedAdd(null, null)), entry('-', new CheckedSubtract(null ,null)),
                entry('*', new CheckedMultiply(null, null)), entry('/', new CheckedDivide(null ,null)),
                entry('&', new And(null ,null)), entry('|', new Or(null, null)),
                entry('^', new Xor(null, null)), entry('~', new Not(null)),
                entry('$', new CheckedNegate(null)), entry('(', new CheckedNegate(new Const(0))),
                entry('l', new L0(null)), entry('t', new T0(null)));
        private static final Map<ToMiniStringExpression, Integer> priority
                = Map.ofEntries(entry(new CheckedAdd(null, null), 1), entry(new CheckedSubtract(null, null), 1),
                entry(new CheckedMultiply(null, null), 2), entry(new CheckedDivide(null, null), 2),
                entry(new CheckedNegate(null), 3), entry(new Not(null), 3),
                entry(new And(null, null), -1), entry(new L0(null), 3), entry(new T0(null), 3),
                entry(new Or(null, null), -3), entry(new CheckedNegate(new Const(0)), -4));
        private char prev;

        public Parser(CharSource source) {
            super(source);
            this.stack = new LinkedList<>();
            this.notation = new ArrayList<>();
            this.prev = ' ';
        }
        public TripleExpression parse() throws InvalidExpressionException {
            getNotation();
            return parseNotation();
        }

        private void getNotation() throws InvalidExpressionException {
            while (!eof()) {
                parseOperand();
            }
            while (!stack.isEmpty()) {
                if (stack.peek().equals(new CheckedNegate(new Const(0)))) {
                    throw new InvalidExpressionException("Invalid Expression: extra opening brackets.");
                }
                notation.add(stack.peek());
                stack.pop();
            }
        }

        private ToMiniStringExpression parseNotation() throws InvalidExpressionException {
            ArrayList<ToMiniStringExpression> res = new ArrayList<>();
            if (notation.size() == 1 &&
                    (notation.get(0) instanceof BinaryOperation || notation.get(0) instanceof UnaryOperation)) {
                throw new InvalidExpressionException("Invalid expression: expected operands. Real value: \""
                        + notation.get(0).toString() + "\"");
            }
            res.add(notation.get(0));
            for (int i = 1; i < notation.size(); i++) {
                if (notation.get(i) instanceof Operand) {
                    res.add(notation.get(i));
                } else {
                    if (notation.get(i) instanceof UnaryOperation) {
                        res.add(getOperation(res.get(res.size() - 1), null, notation.get(i)));
                        res.remove(res.size() - 2);
                    } else {
                        res.add(getOperation(res.get(res.size() - 2), res.get(res.size() - 1), notation.get(i)));
                        res.remove(res.size() - 2);
                        res.remove(res.size() - 2);
                    }
                }
            }
            return res.get(0);
        }

        private void parseOperand() throws InvalidExpressionException {
            skipWhitespaces();
            if (safeTake() == 'x' || safeTake() == 'y' || safeTake() == 'z'
                    && (prev == ' ' || prev == '(' || converter.get(prev) instanceof BinaryOperation
                    || converter.get(prev) instanceof UnaryOperation)) {
                prev = take();
                notation.add(new Variable(Character.toString(prev)));
            } else if (Character.isDigit(safeTake()) && (prev == ' ' || prev == '('
                    || converter.get(prev) instanceof BinaryOperation || converter.get(prev) instanceof UnaryOperation)) {
                prev = safeTake();
                notation.add(new Const(takeInteger('0')));
            } else if (converter.containsKey(safeTake()) && safeTake() != '(') {
                stackUpdate();
            } else if (safeTake() == '(') {
                prev = take();
                stack.push(new CheckedNegate(new Const(0)));
            } else if (safeTake() == ')' && !(converter.get(prev) instanceof BinaryOperation)
                    && !(converter.get(prev) instanceof UnaryOperation)) {
                findLeftBracket();
            } else {
                throw new InvalidExpressionException("Invalid expression: Expected: operand or operation. Real value: '" + take() + "'.");
            }
        }

        private ToMiniStringExpression getOperation(ToMiniStringExpression leftExpression, ToMiniStringExpression rightExpression, ToMiniStringExpression operation) {
            if (operation.getClass() == CheckedNegate.class) {
                return new CheckedNegate(leftExpression);
            } else if (operation.getClass() == CheckedAdd.class) {
                return new CheckedAdd(leftExpression, rightExpression);
            } else if (operation.getClass() == CheckedSubtract.class) {
                return new CheckedSubtract(leftExpression, rightExpression);
            } else if (operation.getClass() == CheckedMultiply.class) {
                return new CheckedMultiply(leftExpression, rightExpression);
            } else if (operation.getClass() == CheckedDivide.class) {
                return new CheckedDivide(leftExpression, rightExpression);
            } else if (operation.getClass() == And.class) {
                return new And(leftExpression, rightExpression);
            } else if (operation.getClass() == Xor.class) {
                return new Xor(leftExpression, rightExpression);
            } else if (operation.getClass() == Or.class) {
                return  new Or(leftExpression, rightExpression);
            } else if (operation.getClass() == Not.class) {
                return new Not(leftExpression);
            } else if (operation.getClass() == L0.class) {
                return new L0(leftExpression);
            } else {
                return new T0(leftExpression);
            }
        }

        private void stackUpdate() throws InvalidExpressionException {
            char operation = take();
            if (operation == 'l' || operation == 't') {
                if (safeTake() == '0') {
                    take();
                    if (safeTake() == 'x' || safeTake() == 'y' || safeTake() == 'z' || Character.isDigit(safeTake())) {
                        throw new InvalidExpressionException("Invalid expression: Expected: '(' or ' '. Real value: '" + take() + "'.");
                    }
                } else {
                    throw new InvalidExpressionException("Invalid expression: Expected: '0'. Real value: '" + take() + "'.");
                }
            }
            if (operation == '-' && ifUnaryOperation(operation) && !Character.isDigit(safeTake())) {
                operation = '$';
            }
            if (Character.isDigit(safeTake()) && ifUnaryOperation(operation) && operation == '-') {
                prev = safeTake();
                notation.add(new Const(takeInteger('-')));
            } else if (stack.isEmpty()) {
                skipWhitespaces();
                stack.push(converter.get(operation));
                prev = operation;
            } else {
                skipWhitespaces();
                if ((operation == '-' && !ifUnaryOperation(operation) || operation == '*'
                        || operation == '/' || operation == '+')
                        && (prev == ' ' || prev == '(' || converter.get(prev) instanceof BinaryOperation
                        || converter.get(prev) instanceof UnaryOperation)) {
                    throw new InvalidExpressionException("Invalid expression: Expected: right operand. Real value: '" + operation + "'.");
                }
                while (!stack.isEmpty()
                        && priority.get(converter.get(operation)) <= priority.get(stack.peek())
                        && !ifUnaryOperation(operation)) {
                    notation.add(stack.peek());
                    stack.pop();
                }
                stack.push(converter.get(operation));
                prev = operation;
            }
        }

        private void findLeftBracket() throws InvalidExpressionException {
            while (!stack.isEmpty() && !Objects.equals(stack.peek(), new CheckedNegate(new Const(0)))) {
                notation.add(stack.peek());
                stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.pop();
                prev = take();
                return;
            }
            throw new InvalidExpressionException("Invalid expression: Expected: operation or operand. Real value: '" + take() + "'.");
        }

        private boolean ifUnaryOperation(char ch) {
            return (converter.containsKey(prev) || prev == ' ') && (ch == '-' || ch == '~' || ch == '$');
        }

        private int takeInteger(char prefix) {
            StringBuilder number = new StringBuilder();
            number.append(prefix);
            while (Character.isDigit(safeTake())) {
                number.append(take());
            }
            return Integer.parseInt(number.toString());
        }
    }
}