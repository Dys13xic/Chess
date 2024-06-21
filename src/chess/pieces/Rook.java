package chess.pieces;

import chess.Board;
import chess.Square;

public class Rook extends Piece {

    public Rook(Colour colour) {
        super(colour, Type.ROOK);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2656' : '\u265C';
    }

    // @Override
    // public Square[] legalMoves(Board board) {
    //     // King in check?
    //     // -> If yes
    //     // ---> can piece capture attacker
    //     // ---> can piece block attacker

    //     // Is piece pinned to king?
        
    //     // Are friendly pieces limiting vertical and horizontal movement?
    //     // Are capturable enemy pieces limiting vertical and horizontal movement?
        

    //     // Castling...
        
    // }
    
}
