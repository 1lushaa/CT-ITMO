package expression.exceptions;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(new ExpressionParser().parse("l0 0").toString());
        } catch (InvalidExpressionException e) {
            System.out.println(e.getMessage());
        }
    }
}
