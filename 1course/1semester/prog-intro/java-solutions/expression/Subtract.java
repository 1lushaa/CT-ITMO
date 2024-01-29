package expression;

public class Subtract extends BinaryOperation {
    public Subtract(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "-");
    }

    @Override
    protected int eval(int left, int right) {
        return left - right;
    }
}
