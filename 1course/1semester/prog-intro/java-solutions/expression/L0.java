package expression;

public class L0 extends UnaryOperation {
    public L0(ToMiniStringExpression Exp) {
        super(Exp, "l0");
    }

    @Override
    protected int eval(int val) {
        if (val == 0) {
            return 32;
        }
        return 32 - Integer.toBinaryString(val).length();
    }
}
