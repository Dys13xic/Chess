package chess.pieces;

import chess.Board;
import chess.Square;

public class Knight extends Piece {

    public Knight(Colour colour, int rank, int file) {
        super(colour, Type.KNIGHT, rank, file);
    }

    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2658' : '\u265E';
    }

    // @Override
    // public Square[] legalMoves(Board board) {
    //     // King in check?
    //     // -> If yes
    //     // ---> can piece capture attacker
    //     // ---> can piece block attacker

    //     // Is piece pinned to king?
        
    //     // Are friendly pieces limiting movement?  
    // }
  
}
