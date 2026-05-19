package chess;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public enum Status {
        IN_PROGRESS,
        WHITE_WIN,
        BLACK_WIN,
        STALEMATE,
        DRAW
    }

    private Position currentPosition;
    private ArrayList<Move> moveHistory;
    private Status result = Status.IN_PROGRESS;

    private boolean isActive() {
        return this.result == Status.IN_PROGRESS;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isActive()) {
            currentPosition.draw();
            System.out.print("Enter a move: ");
            Move tentativeMove = new Move(scanner.nextLine());
        }
        scanner.close();
    }
}