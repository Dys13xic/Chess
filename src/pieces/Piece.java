package pieces;

public abstract class Piece {
    public enum Colour {
        WHITE,
        BLACK
    }
    public enum Type {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }
    // TODO determine if Piece needs to be included in pieces package
    private Colour colour;
    private Type type;
    private int rank;
    private int file;

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
    
}
