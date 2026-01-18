package chess.positionformats;

import chess.pieces.Piece;
import chess.Move;

import java.util.ArrayList;

public interface PositionFormats {
    public void loadPosition(chess.Board chessBoard);
    public ArrayList<Move> getMoves();
    public Piece.Colour getActivePlayer();
    public int getHalfMoveClock();
    public String export(chess.Board chessBoard);
}