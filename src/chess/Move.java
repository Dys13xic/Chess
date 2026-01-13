package chess;

import chess.pieces.Piece;

public class Move {
    
    public enum Type {
        STANDARD,
        CASTLE,
        OFFER_DRAW,
        RESULT
    }

    int move;
    Type type;
    Piece selected;
    Piece captured;
    Piece promotedTo;
    boolean check;
    boolean mate;
    String notation;

    public Move(String text) {

    }
}
