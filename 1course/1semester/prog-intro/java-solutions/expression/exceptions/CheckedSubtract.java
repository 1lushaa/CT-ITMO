package expression.exceptions;

import expression.BinaryOperation;
import expression.ToMiniStringExpression;

public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "-");
    }

    @Override
    protected int eval(int left, int right) throws TypeOverflowException {
        if (right < 0 && left > Integer.MAX_VALUE + right
                || right > 0 && left < Integer.MIN_VALUE + right) {
            throw new TypeOverflowException("overflow");
        }
        return left - right;
    }
}
