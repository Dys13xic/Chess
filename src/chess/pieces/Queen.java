package chess.pieces;

import java.util.ArrayList;

import chess.BoardView;
import chess.PseudoLegalMove;

public class Queen extends Piece {

    private static final int[][] MOVEMENT_VECTORS = {
        // Axes
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1},
        // Diagonals
        {1, 1},
        {1, -1},
        {-1, 1},
        {-1, -1}
    };

    public Queen(Colour colour) {
        super(colour, Type.QUEEN);
    }

    public int[][] getMovementVectors() {
        return MOVEMENT_VECTORS;
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2655' : '\u265B';
    }
    @Override
    public ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board) {
        return getPseudoLegalMovesFromVector(board, getMovementVectors());
    }
}