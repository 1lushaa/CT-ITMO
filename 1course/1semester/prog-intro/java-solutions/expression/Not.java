package expression;

public class Not extends UnaryOperation {
    public Not (ToMiniStringExpression Exp) {
        super(Exp, "~");
    }

    @Override
    protected int eval(int val) {
        return ~val;
    }
}
