package expression;

import java.util.Objects;

public abstract class BinaryOperation implements ToMiniStringExpression {

    private final ToMiniStringExpression firstExp;
    private final ToMiniStringExpression secondExp;
    private final String operation;

    public BinaryOperation(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp, String operation) {
        this.firstExp = firstExp;
        this.secondExp = secondExp;
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
        return eval(firstExp.evaluate(val), secondExp.evaluate(val));
    }

    @Override
    public int evaluate (int x, int y, int z) {
        return eval(firstExp.evaluate(x, y, z), secondExp.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object op) {
        if (op != null && this.getClass() == op.getClass()) {
            BinaryOperation newBinaryOperation = (BinaryOperation) op;
            return Objects.equals(firstExp, newBinaryOperation.firstExp)
                    && Objects.equals(this.operation, newBinaryOperation.operation)
                    && Objects.equals(secondExp, newBinaryOperation.secondExp)
                    && hashCode() == newBinaryOperation.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstExp, secondExp, operation);
    }

    @Override
    public void toString(StringBuilder sb) {
        sb.append("(");
        firstExp.toString(sb);
        sb.append(' ').append(operation).append(' ');
        secondExp.toString(sb);
        sb.append(")");
    }

    protected abstract int  eval(int left, int right);

}