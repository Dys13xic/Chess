package chess;
import java.util.ArrayList;

import chess.pieces.Piece;
import chess.positionformats.Fen;
import chess.positionformats.PositionFormats;

public class Position {
    private Board board;
    private int moveCount;
    private int halfMoveClock;
    private byte castleRights;
    private Piece.Colour activePlayer;


    public Position() {
        this(new Fen(Fen.STANDARD_POSITION));
    }

    public Position(PositionFormats format) {
        board = new Board();

        format.loadPosition(board);
        moveCount = format.getMoves().size();
        halfMoveClock = format.getHalfMoveClock();
        activePlayer = format.getActivePlayer();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getHalfMoveClock() {
        return halfMoveClock;
    }

    public Piece.Colour getActivePlayer() {
        return activePlayer;
    }

    public void draw() {
        Graphics.drawBoard(board);
        // TODO add any other visuals (i.e. pieces captured, relative piece value, past moves, etc.)
    }
}