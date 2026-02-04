package chess;

import chess.pieces.Piece;

public class PseudoLegalMove {

    public enum Type {
        QUIET,
        CAPTURE
    }

    Type type;
    Coordinate source;
    Coordinate target;
    Piece.Type promotion;

    public PseudoLegalMove(Type type, Coordinate source, Coordinate target, Piece.Type promotion) {
        this.type = type;
        this.source = source;
        this.target = target;
        this.promotion = promotion;
    }
}
