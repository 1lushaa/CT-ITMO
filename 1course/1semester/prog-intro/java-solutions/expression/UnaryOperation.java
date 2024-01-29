package expression;

import java.util.Objects;

public abstract class UnaryOperation implements ToMiniStringExpression {
    private final ToMiniStringExpression expression;
    private final String operation;

    public UnaryOperation(ToMiniStringExpression expression, String operation) {
        this.expression = expression;
        this.operation = operation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    @Override
    public int evaluate (int val) {
        return eval(expression.evaluate(val));
    }

    @Override
    public int evaluate (int x, int y, int z) {
        return eval(expression.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object op) {
        if (op != null && this.getClass() == op.getClass()) {
            UnaryOperation newUnaryOperation = (UnaryOperation) op;
            return hashCode() == newUnaryOperation.hashCode()
                    && Objects.equals(expression, newUnaryOperation.expression)
                    && Objects.equals(this.operation, newUnaryOperation.operation);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression, operation);
    }

    @Override
    public void toString(StringBuilder sb) {
        sb.append(operation).append('(');
        expression.toString(sb);
        sb.append(')');
    }

    protected abstract int  eval(int val);

}
