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

    public PseudoLegalMove(PseudoLegalMove move, Piece.Type promotion) {
        this.capture = move.getCapture();
        this.source = move.getSource();
        this.target = move.getTarget();
        this.promotion = promotion;
    }

    public Piece getCapture() {
        return capture;
    }

    public Coordinate getSource() {
        return source;
    }

    public Coordinate getTarget() {
        return target;
    }

    public Piece.Type promotedTo() {
        return promotion;
    }
}