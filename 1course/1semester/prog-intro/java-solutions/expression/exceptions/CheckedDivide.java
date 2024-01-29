package expression.exceptions;

import expression.BinaryOperation;
import expression.ToMiniStringExpression;
import expression.common.Type;

public class CheckedDivide extends BinaryOperation {
    public CheckedDivide(ToMiniStringExpression firstExp, ToMiniStringExpression secondExp) {
        super(firstExp, secondExp, "/");
    }

    @Override
    protected int eval(int left, int right) throws InvalidOperationException, TypeOverflowException {
        if (right == 0) {
            throw new InvalidOperationException("division by zero");
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new TypeOverflowException("overflow");
        }
        return left / right;
    }
}
