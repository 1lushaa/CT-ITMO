package game;

public class Move {
    private final int row;
    private final int col;
    private final Cell cell;

    public Move(final int row, final int col, final Cell cell) {
        this.row = row;
        this.col = col;
        this.cell = cell;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getSide() {
        return cell;
    }
}
