package game;

public class FakeBoard implements Position {

    MNKBoard board;

    public FakeBoard(MNKBoard board) {
        this.board = board;
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public String getField() {
        return board.getField();
    }

}
