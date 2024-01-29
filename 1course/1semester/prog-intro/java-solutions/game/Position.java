package game;

public interface Position {
    boolean isValid(Move move);

    String getField();
}