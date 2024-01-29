package expression;

public class Negate extends UnaryOperation {

    public Negate(ToMiniStringExpression Exp) {
        super(Exp, "-");
    }

    @Override
    protected int eval(int val) {
        return -val;
    }

}
