package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Colour;;

public class Player {
    
    private Colour colour;
    private Piece[] pieces;

    public Colour getColour() {
        return colour;
    }

    public Piece[] getPieces() {
        return pieces;
    }
}