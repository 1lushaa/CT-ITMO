package expression;

public class And extends BinaryOperation {
    public And(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "&");
    }

    @Override
    protected int eval(int left, int right) {
        return left & right;
    }
}
