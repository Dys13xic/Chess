package chess.pieces;

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

    public Piece(Colour colour, Type type) {
        this.colour = colour;
        this.type = type;
    }

    public Colour getColour() {
        return colour;
    }

    public Type getType() {
        return type;
    }

    public abstract char getSymbol();

    public abstract boolean validMovementPattern(int currentRank, int currentFile);
}