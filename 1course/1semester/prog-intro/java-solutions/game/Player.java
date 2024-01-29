package game;

public interface Player {
    Move move(Cell cell, Position position);

    String getName();
}