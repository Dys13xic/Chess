package chess.pieces;

import java.util.ArrayList;

import chess.BoardView;
import chess.PseudoLegalMove;

public class Bishop extends Piece {

    private static final int[][] MOVEMENT_VECTORS = {
        {1, 1},
        {1, -1},
        {-1, 1},
        {-1, -1}
    };

    public Bishop(Colour colour) {
        super(colour, Type.BISHOP);
    }

    public int[][] getMovementVectors() {
        return MOVEMENT_VECTORS;
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2657' : '\u265D';
    }

    @Override
    public ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board) {
        return getPseudoLegalMovesFromVector(board, getMovementVectors());
    }
}