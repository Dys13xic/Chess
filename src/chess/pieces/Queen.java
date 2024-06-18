package chess.pieces;

import chess.Board;
import chess.Square;

public class Queen extends Piece {

    public Queen(Colour colour, int rank, int file) {
        super(colour, Type.QUEEN, rank, file);
    }

    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2655' : '\u265B';
    }

    // @Override
    // public Square[] legalMoves(Board board) {
    //     // King in check?
    //     // -> If yes
    //     // ---> can piece capture attacker
    //     // ---> can piece block attacker

    //     // Is piece pinned to king?
        
    //     // Are friendly pieces limiting diagonal/vertical/horizontal movement?

    //     // Are capturable enemy pieces limiting diagonal/vertical/horizontal movement?
    // }
    
}
