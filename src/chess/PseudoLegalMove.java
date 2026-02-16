package chess;

import chess.pieces.Piece;

public class PseudoLegalMove {

    Piece capture;
    Coordinate source;
    Coordinate target;
    Piece.Type promotion;

    public PseudoLegalMove(Piece capture, Coordinate source, Coordinate target, Piece.Type promotion) {
        this.capture = capture;
        this.source = source;
        this.target = target;
        this.promotion = promotion;
    }
}
