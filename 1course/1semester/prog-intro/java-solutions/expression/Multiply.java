package expression;

public class Multiply extends BinaryOperation {
    public Multiply(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "*");
    }

    @Override
    protected int eval(int left, int right) {
        return left * right;
    }
}
