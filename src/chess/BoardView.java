package chess;

import chess.pieces.Piece;

public interface BoardView {
    public int[] getPieceSquare(Piece piece);
    public Piece getPieceAt(int rank, int file);
}