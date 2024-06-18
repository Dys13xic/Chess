package chess.pieces;

import chess.Board;
import chess.Square;

public class King extends Piece {

    private boolean inCheck;

    public King(Colour colour, int rank, int file, boolean inCheck) {
        super(colour, Type.KING, rank, file);
        this.inCheck = inCheck;
    }

    public boolean getInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2654' : '\u265A';
    }

    // @Override
    // public Square[] legalMoves(Board board) {
    //     // Are friendly pieces limiting diagonal/vertical/horizontal movement?

    //     // Are enemy pieces attacking squares?
    // }
    
}
