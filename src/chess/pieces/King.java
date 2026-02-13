package chess.pieces;

import java.util.ArrayList;

import chess.BoardView;
import chess.PseudoLegalMove;

public class King extends Piece {

    private boolean inCheck;
    private boolean moved;

    public King(Colour colour, boolean inCheck, boolean moved) {
        super(colour, Type.KING);
        this.inCheck = inCheck;
        this.moved = moved;
    }

    public boolean getInCheck() {
        return inCheck;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2654' : '\u265A';
    }

    @Override
    public ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board) {
        int[][] deltas = {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};
        return getPseudoLegalMovesFromDelta(board, deltas);
    }
}
