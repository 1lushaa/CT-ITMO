package expression.exceptions;

public class TypeOverflowException extends NumberFormatException{
    public TypeOverflowException(String message) {
        super(message);
    }
}
