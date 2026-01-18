package chess;

import java.util.ArrayList;
import java.util.Scanner;

import chess.pieces.Piece;
import chess.positionformats.Fen;
import chess.positionformats.PositionFormats;

public class Game {
    private boolean active = true;
    private Board chessBoard;
    private ArrayList<Move> moves;
    private Piece.Colour activePlayer;
    private int halfMoveClock;

    private static final String STANDARD_POSITION_FEN = "rnbqkbnr/pppppppp/////PPPPPPPP/RNBQKBNR w KQkq - 0 1";


    public Game() {
        this(new Fen(STANDARD_POSITION_FEN));
    }

    public Game(PositionFormats format) {
        chessBoard = new Board();

        format.loadPosition(chessBoard);
        moves = format.getMoves();
        activePlayer = format.getActivePlayer();
        halfMoveClock = format.getHalfMoveClock();
    }

    public boolean isActive() {
        return active;
    }

    public int getMoveCount() {
        return moves.size();
    }

    int getHalfMoveClock() {
        return halfMoveClock;
    }

    public Piece.Colour getActivePlayer() {
        return activePlayer;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isActive()) {
            Graphics.drawBoard(chessBoard);
            System.out.print("Enter a move: ");
            Move tentativeMove = new Move(scanner.nextLine());
            // Board.move(tentativeMove);
        }
        scanner.close();
    }
}
