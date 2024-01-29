package expression.exceptions;

import expression.BinaryOperation;
import expression.ToMiniStringExpression;

public class CheckedMultiply extends BinaryOperation {
    public CheckedMultiply(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "*");
    }

    @Override
    protected int eval(int left, int right) throws TypeOverflowException {
        if (left > 0 && right > 0 && Integer.MAX_VALUE / left < right
                || left < 0 && right < 0 && Integer.MAX_VALUE / left > right
                || left > 0 && right < 0 && Integer.MIN_VALUE / left > right
                || left < 0 && right > 0 && Integer.MIN_VALUE / right > left) {
            throw new TypeOverflowException("Overflow");
        }
        return left * right;
    }
}
