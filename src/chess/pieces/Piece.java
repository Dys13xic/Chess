package chess.pieces;
import chess.Board;
// import chess.Colour;
import chess.Square;

public abstract class Piece {

    public enum Colour {
        WHITE("\033[38;5;0m"),
        BLACK("\033[38;5;0m");

        public final String ansiString;

        private Colour(String ansiString) {
            this.ansiString = ansiString;
        }
    }

    public enum Type {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }

    private Colour colour;
    private Type type;
    private int rank;
    private int file;

    public Piece(Colour colour, Type type, int rank, int file) {
        this.colour = colour;
        this.type = type;
        this.rank = rank;
        this.file = file;
    }

    public Colour getColour() {
        return colour;
    }

    public Type getType() {
        return type;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public abstract char getSymbol();

    // public abstract Square[] legalMoves(Board board);

}