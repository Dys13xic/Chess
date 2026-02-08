package chess.pieces;

import java.util.ArrayList;

import chess.BoardView;
import chess.PseudoLegalMove;

public class Bishop extends Piece {

    public Bishop(Colour colour) {
        super(colour, Type.BISHOP);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2657' : '\u265D';
    }

    @Override
    public ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board) {
        return getPseudoLegalMovesAlongDiagonals(board);
    }
}