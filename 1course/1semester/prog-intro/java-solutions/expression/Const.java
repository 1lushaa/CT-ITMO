package expression;

public class Const extends Operand {

    public Const(int constant) {
        super(Integer.toString(constant));
    }

    @Override
    protected int eval(String operator, int val) {
        return Integer.parseInt(operator);
    }

    @Override
    protected int eval(String operator, int x, int y, int z) {
        return Integer.parseInt(operator);
    }

}