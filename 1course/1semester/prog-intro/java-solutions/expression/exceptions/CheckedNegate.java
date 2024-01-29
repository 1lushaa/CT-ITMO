package expression.exceptions;

import expression.ToMiniStringExpression;
import expression.UnaryOperation;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(ToMiniStringExpression Exp) {
        super(Exp, "-");
    }

    @Override
    protected int eval(int val) {
        if (val == Integer.MIN_VALUE) {
            throw new TypeOverflowException("overflow");
        }
        return -val;
    }

}
