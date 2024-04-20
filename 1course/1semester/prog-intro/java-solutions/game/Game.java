package game;

import java.util.Scanner;

public class Game {

    public void play() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Input number or rows, columns and symbols to win: ");
                int n = Integer.parseInt(in.next());
                int m = Integer.parseInt(in.next());
                int k = Integer.parseInt(in.next());
                System.out.print("Input number of players: ");
                int numberOfPlayers = Integer.parseInt(in.next());
                OlympicSystem olympicSystem = new OlympicSystem(n, m, k, numberOfPlayers);
                olympicSystem.play();
                break;
            } catch (NumberFormatException e) {
                System.out.println(System.lineSeparator() + "Invalid input! Try again!" + System.lineSeparator());
            }
        }
    }

}
