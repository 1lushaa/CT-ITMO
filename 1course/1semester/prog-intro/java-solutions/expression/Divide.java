package expression;

public class Divide extends BinaryOperation {
    public Divide(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "/");
    }

    @Override
    protected int eval(int left, int right) {
        return left / right;
    }
}
