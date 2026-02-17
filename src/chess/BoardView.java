package chess;

import chess.pieces.Piece;

public interface BoardView {
    public Coordinate getPieceCoordinate(Piece piece);
    public Piece getPieceAt(int rank, int file);
    public Piece getPieceAt(Coordinate coordinate);
    public boolean inBounds(int rank, int file);
    public boolean inBounds(Coordinate coordinate);
    public boolean isPromotionSquare(Piece.Colour colour, Coordinate target);
}