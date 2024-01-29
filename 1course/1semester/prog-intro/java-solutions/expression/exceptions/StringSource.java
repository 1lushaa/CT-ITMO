package expression.exceptions;

public class StringSource implements CharSource {
    private final String source;
    private int pos;
    public StringSource(final String source) {
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return pos < source.length();
    }

    @Override
    public char next() {
        return source.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }
}
