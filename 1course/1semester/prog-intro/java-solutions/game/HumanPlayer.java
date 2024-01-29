package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {

    private final Scanner in;
    private final PrintStream out;
    private final String name;

    public HumanPlayer(final String name) {
        this.in = new Scanner(System.in);
        this.out = System.out;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move move(final Cell cell, final Position position) {
        while (true) {
            out.println(System.lineSeparator() + "It's player " + this.name + " turn.");
            out.print(position.getField());
            out.print("Enter row and column: ");
            try {
                int row = Integer.parseInt(in.next());
                int col = Integer.parseInt(in.next());
                Move move = new Move(row - 1, col - 1, cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println(System.lineSeparator() + "Move is invalid! Try again!");
            } catch (NumberFormatException e) {
                out.println(System.lineSeparator() + "Invalid input! Try again!");
            }
        }
    }

}