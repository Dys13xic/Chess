package chess;

import chess.pieces.Piece;

public class Move {
    
    // Types of moves
    // - Standard
    // - Castle
    // - Offer Draw
    // - Final Result

    // Additional flags
    // - Capture
    // - Check
    // - Mate
    // - Promotion

    public enum Type {
        STANDARD,
        CASTLE,
        DRAW_OFFER,
        RESULT
        
    }

    String text;
    Piece target;

    Piece capture;
    Piece promotion;

    boolean check;
    boolean mate;

    public Move(String text) {

    }
}
