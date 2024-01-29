package expression;

import java.util.Objects;

public abstract class Operand implements ToMiniStringExpression {

    private final String operator;
    public Operand(final String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator;
    }

    @Override
    public void toString(StringBuilder sb) {
        sb.append(operator);
    }

    @Override
    public String toMiniString() {
        return operator;
    }

    @Override
    public int evaluate(int val) {
        return eval(operator, val);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return eval(operator, x, y, z);
    }

    @Override
    public boolean equals(Object op) {
        if (op != null && this.getClass() == op.getClass()) {
            Operand newOperand = (Operand) op;
            return Objects.equals(this.operator, newOperand.operator) && hashCode() == newOperand.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator);
    }

    protected abstract int eval(String operator, int val);

    protected abstract int eval(String operator, int x, int y, int z);

}