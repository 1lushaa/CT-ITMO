package game;

import java.util.Map;

public class MNKBoard implements Board, Position {

    private static final Map<Cell, Character> map = Map.of(Cell.X, 'X', Cell.O, 'O', Cell.CELL_IS_EMPTY, '.');
    private final int k;
    private final int nRows;
    private final int nCols;
    private final Cell[][] board;
    private int filledCells = 0;

    public MNKBoard(final int nRows, final int nCols, final int k) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.k = k;
        board = new Cell[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                board[i][j] = Cell.CELL_IS_EMPTY;
            }
        }
    }

    @Override
    public Position getPosition() {
        return new FakeBoard(this);
    }

    public Result makeMove(final Move move) {
        if (isValid(move)) {
            filledCells++;
            board[move.getRow()][move.getCol()] = move.getSide();
            return check(move.getRow(), move.getCol(), move.getSide());
        }
        throw new IllegalStateException("Invalid Move!");
    }

    @Override
    public boolean isValid(final Move move) {
        return move.getRow() < nRows && move.getCol() < nCols
                && move.getRow() >= 0 && move.getCol() >= 0
                && board[move.getRow()][move.getCol()] == Cell.CELL_IS_EMPTY;
    }

    private Result checkLine(final int row, final int col, final Cell cell, final int rowEps, final int colEps) {
        if (directionCheck(row, col, cell, rowEps, colEps) + directionCheck(row, col, cell, -rowEps, -colEps) - 1 >= k) {
            return Result.WIN;
        }
        return Result.UNKNOWN;
    }

    private Result check(final int row, final int col, final Cell cell) {
        if (
                checkLine(row, col, cell, 0, 1) == Result.WIN
                        || checkLine(row, col, cell, 1, 0) == Result.WIN
                        || checkLine(row, col, cell, 1, -1) == Result.WIN
                        || checkLine(row, col, cell, 1, 1) == Result.WIN
        ) {
            return Result.WIN;
        } else if (filledCells == nRows * nCols) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    @Override
    public String getField() {
        StringBuilder field = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                field.append(map.get(board[i][j]));
            }
            field.append(System.lineSeparator());
        }
        return field.toString();
    }

    private int directionCheck(final int row, final int col, final Cell cell, final int rowEps, final int colEps) {
        int cnt = 0;
        int newRow = row;
        int newCol = col;
        while ((cnt - 1 < k) && (newRow < nRows) && (newCol < nCols)
                && (newRow >= 0) && (newCol >= 0)
                && (board[newRow][newCol] == cell)) {
            cnt++;
            newRow += rowEps;
            newCol += colEps;
        }
        return cnt;
    }

}