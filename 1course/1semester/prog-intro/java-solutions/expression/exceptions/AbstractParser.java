package expression.exceptions;

public class AbstractParser {
    private final CharSource source;
    private final static char END = '\0';
    private char ch;

    protected AbstractParser(CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char res = ch;
        ch = source.hasNext() ? source.next() : END;
        return res;
    }

    protected boolean test(char expected) {
        return expected == ch;
    }

    protected boolean take(char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected char safeTake() {
        return ch;
    }

    protected boolean eof() {
        return take(END);
    }

    protected void expect(char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void skipWhitespaces() {
        while (Character.isWhitespace(safeTake())) {
            take();
        }
    }

    protected IllegalArgumentException error(String message) {
        return new IllegalArgumentException(message);
    }
}
