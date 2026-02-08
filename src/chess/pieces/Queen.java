package chess.pieces;

import java.util.ArrayList;

import chess.BoardView;
import chess.PseudoLegalMove;

public class Queen extends Piece {

    public Queen(Colour colour) {
        super(colour, Type.QUEEN);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2655' : '\u265B';
    }
    @Override
    public ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board) {
        ArrayList<PseudoLegalMove> moves = new ArrayList<PseudoLegalMove>();
        moves.addAll(getPseudoLegalMovesAlongAxes(board));
        moves.addAll(getPseudoLegalMovesAlongDiagonals(board));
        return moves;
    }
}