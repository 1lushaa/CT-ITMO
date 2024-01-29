package expression.parser;

import expression.*;
import expression.exceptions.InvalidExpressionException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;


public final class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(String expression) {
        return new Parser(new StringSource(expression)).parse();
    }

    private static class Parser extends AbstractParser {
        private final LinkedList<ToMiniStringExpression> stack;
        private final ArrayList<ToMiniStringExpression> notation;
        private static final Map<Character, ToMiniStringExpression> converter
                = Map.of('+', new Add(null, null), '-', new Subtract(null ,null),
                '*', new Multiply(null, null), '/', new Divide(null ,null),
                '&', new And(null ,null), '|', new Or(null, null),
                '^', new Xor(null, null), '~', new Not(null), '$', new Negate(null),
                '(', new Negate(new Const(0)));
        private static final Map<ToMiniStringExpression, Integer> priority
                = Map.of(new Add(null, null), 1, new Subtract(null, null), 1,
                new Multiply(null, null), 2, new Divide(null, null), 2,
                new Negate(null), 3, new Not(null), 3,
                new And(null, null), -1, new Xor(null, null), -2,
                new Or(null, null), -3, new Negate(new Const(0)), -4);
        private char prev;

        public Parser(CharSource source) {
            super(source);
            this.stack = new LinkedList<>();
            this.notation = new ArrayList<>();
            this.prev = ' ';
        }
        public TripleExpression parse() {
            getNotation();
            return parseNotation();
        }

        private void getNotation() {
            while (!eof()) {
                parseOperand();
            }
            while (!stack.isEmpty()) {
                notation.add(stack.peek());
                stack.pop();
            }
        }

        private ToMiniStringExpression parseNotation() {
            ArrayList<ToMiniStringExpression> res = new ArrayList<>();
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

        private void parseOperand() {
            skipWhitespaces();
            if (safeTake() == 'x' || safeTake() == 'y' || safeTake() == 'z') {
                prev = take();
                notation.add(new Variable(Character.toString(prev)));
            } else if (Character.isDigit(safeTake())) {
                prev = safeTake();
                notation.add(new Const(takeInteger('0')));
            } else if (converter.containsKey(safeTake()) && safeTake() != '(') {
                stackUpdate();
            } else if (safeTake() == '(') {
                prev = take();
                stack.push(new Negate(new Const(0)));
            } else if (safeTake() == ')') {
                findLeftBracket();
            } else {
                throw error("Expected: operand or operation. Real value: " + take() + ".");
            }
            skipWhitespaces();
        }

        private ToMiniStringExpression getOperation(ToMiniStringExpression leftExpression, ToMiniStringExpression rightExpression, ToMiniStringExpression operation) {
            if (operation.getClass() == Negate.class) {
                return new Negate(leftExpression);
            } else if (operation.getClass() == Add.class) {
                return new Add(leftExpression, rightExpression);
            } else if (operation.getClass() == Subtract.class) {
                return new Subtract(leftExpression, rightExpression);
            } else if (operation.getClass() == Multiply.class) {
                return new Multiply(leftExpression, rightExpression);
            } else if (operation.getClass() == Divide.class) {
                return new Divide(leftExpression, rightExpression);
            } else if (operation.getClass() == And.class) {
                return new And(leftExpression, rightExpression);
            } else if (operation.getClass() == Xor.class) {
                return new Xor(leftExpression, rightExpression);
            } else if (operation.getClass() == Or.class) {
                return  new Or(leftExpression, rightExpression);
            } else {
                return new Not(leftExpression);
            }
        }

        private void stackUpdate() {
            char operation = take();
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

        private void findLeftBracket() {
            while (!stack.isEmpty() && !Objects.equals(stack.peek(), new Negate(new Const(0)))) {
                notation.add(stack.peek());
                stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.pop();
                prev = take();
                return;
            }
            throw new IllegalArgumentException("Couldn't find closing bracket: Expected : ')'. Real value: " + take() + ".");
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