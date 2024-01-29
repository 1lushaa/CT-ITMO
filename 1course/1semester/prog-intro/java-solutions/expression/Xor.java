package expression;

public class Xor extends BinaryOperation {
    public Xor(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "^");
    }

    @Override
    protected int eval(int left, int right) {
        return left ^ right;
    }
}
