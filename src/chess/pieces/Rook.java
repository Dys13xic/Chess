package chess.pieces;

import java.util.ArrayList;

import chess.BoardView;
import chess.PseudoLegalMove;

public class Rook extends Piece {

    private static final int[][] MOVEMENT_VECTORS = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };
    boolean moved;

    public Rook(Colour colour, boolean moved) {
        super(colour, Type.ROOK);
        this.moved = moved;
    }

    public int[][] getMovementVectors() {
        return MOVEMENT_VECTORS;
    }

    public boolean hasMoved() {
        return moved;
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2656' : '\u265C';
    }

    @Override
    public ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board) {
        return getPseudoLegalMovesFromVector(board, getMovementVectors());
    }
}