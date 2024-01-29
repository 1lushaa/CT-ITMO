package expression;

public class Or extends BinaryOperation {
    public Or(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "|");
    }

    @Override
    protected int eval(int left, int right) {
        return left | right;
    }
}
