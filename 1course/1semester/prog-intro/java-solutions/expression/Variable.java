package expression;

public class Variable extends Operand {

    public Variable(String variable) {
        super(variable);
    }

    @Override
    protected int eval(String operator, int val) {
        return val;
    }

    @Override
    protected int eval(String operator, int x, int y, int z) {
        return switch (operator) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> 0;
        };
    }

}