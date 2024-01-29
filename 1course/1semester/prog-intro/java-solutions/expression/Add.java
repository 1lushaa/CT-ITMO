package expression;

public class Add extends BinaryOperation {

    public Add(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "+");
    }

    @Override
    protected int eval(int left, int right) {
        return left + right;
    }
}
