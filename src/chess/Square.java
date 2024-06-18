package chess;
import chess.pieces.Piece;

public class Square {

    public enum Colour {
        LIGHT("\033[48;5;222m"),
        DARK("\033[48;5;94m");

        public final String ansiString;

        private Colour(String ansiString) {
            this.ansiString = ansiString;
        }
    }

    private Colour colour;
    private Piece piece;

    Square(Colour colour, Piece piece) {
        this.colour = colour;
        this.piece = piece;
    }

    public Colour getColour() {
        return colour;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean hasPiece() {
        return piece == null;
    }
}