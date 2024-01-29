package expression.exceptions;

import expression.BinaryOperation;
import expression.ToMiniStringExpression;

public class CheckedAdd extends BinaryOperation {

    public CheckedAdd(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "+");
    }

    @Override
    protected int eval(int left, int right) throws TypeOverflowException {
        if (left > Integer.MAX_VALUE - right && right >= 0 && left >= 0
                || left < 0 && right < 0 && (left < Integer.MIN_VALUE - right)) {
            throw new TypeOverflowException("Overflow!");
        }
        return left + right;
    }
}
