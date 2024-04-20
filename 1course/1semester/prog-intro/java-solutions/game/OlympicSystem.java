package game;

import java.util.ArrayList;

public class OlympicSystem {
    private final ArrayList<Player> players;

    private final int n, m, k;

    public OlympicSystem(int n, int m, int k, int numberOfPlayers) {
        this.n = n;
        this.k = k;
        this.m = m;
        ArrayList<Player> pl = new ArrayList<Player>();
        for (int i = 0; i < numberOfPlayers; i++) {
            pl.add(new HumanPlayer(Integer.toString(i + 1)));
        }
        players = pl;
    }

    public void play() {
        if (!ifPowerOf2(players.size())) {
            System.out.println("Let's start regular season!" + System.lineSeparator());
            for (int i = 0; i + 1 < players.size(); i++) {
                match(players.get(i), players.get(i + 1));
                if (ifPowerOf2(players.size())) {
                    break;
                }
            }
            System.out.println("It's the end of the regular season!");
            tournament();
        } else {
            tournament();
        }
    }

    public void tournament() {
        System.out.println(System.lineSeparator() + "Let's start tournament!" + System.lineSeparator());
        while (players.size() > 1) {
            circle();
        }
        System.out.println("Player " + players.get(0).getName() + " is winner of tournament!");
    }

    public void circle() {
        System.out.println("Les's start new circle!" + System.lineSeparator());
        for (int i = 0; i + 1 < players.size(); i++) {
            match(players.get(i), players.get(i + 1));
        }
    }

    public void match(Player player1, Player player2) {
        final MNKBoard board = new MNKBoard(n, m, k);
        System.out.println("It's match between player " + player1.getName() + " and player " + player2.getName() + '.');
        int firstPlayerSide = (int) Math.round(Math.random() * 1);
        if (firstPlayerSide == 0) {
            playersMoves(board, player1, player2);
        } else {
            playersMoves(board, player2, player1);
        }
    }

    public void playersMoves(MNKBoard board, Player player1, Player player2) {
        while (true) {
            try {
                if (resultCheck(board.makeMove(player1.move(Cell.X, board.getPosition())), player1, board, player2)) {
                    players.remove(player2);
                    break;
                }
                try {
                    if (resultCheck(board.makeMove(player2.move(Cell.O, board.getPosition())), player2, board, player1)) {
                        players.remove(player1);
                        break;
                    }
                } catch (IllegalStateException e) {
                    System.out.println("Player " + player1.getName() + " is winner of this match!");
                    players.remove(player2);
                    break;
                }
            } catch (IllegalStateException e) {
                System.out.println("Player " + player2.getName() + " is winner of this match!");
                players.remove(player1);
                break;
            }
        }
    }

    private boolean resultCheck(final Result result, final Player player1, final Position board, final Player player2) {
        switch (result) {

            case WIN:
                System.out.println(System.lineSeparator() + board.getField());
                System.out.println("Player " + player1.getName() + " is winner of this match!" + System.lineSeparator());
                return true;
            case DRAW:
                System.out.println(System.lineSeparator() + board.getField());
                System.out.println("Draw! Play again!" + System.lineSeparator());
                match(player1, player2);
            default:
                return false;
        }
    }

    private boolean ifPowerOf2(int num) {
        int power = 2;
        while (power < num) {
            power *= 2;
        }
        return power == num;
    }
}
