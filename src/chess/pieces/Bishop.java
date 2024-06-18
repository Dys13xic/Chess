package chess.pieces;

import chess.Board;
import chess.Square;

public class Bishop extends Piece {

    public Bishop(Colour colour, int rank, int file) {
        super(colour, Type.BISHOP, rank, file);
    }

    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2657' : '\u265D';
    }

    // @Override
    // public Square[] legalMoves(Board board) {
    //     // King in check?
    //     // -> If yes
    //     // ---> can piece capture attacker
    //     // ---> can piece block attacker

    //     // Is piece pinned to king?
        
    //     // Are friendly pieces limiting diagonal movement?
    //     // Are capturable enemy pieces limiting diagonal movement?

    //     // Check diagonally to the top-left, bottom-right, top-right, bottom left.
    //     int tempRank = this.getRank();
    //     int tempFile = this.getFile();

    //     for (r = this.getRank(), f = this.getFile(); r < 8 && ; r++) {

    //     }
        
    // }
    
}
