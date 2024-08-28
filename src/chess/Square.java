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
    private int rank;
    private int file;

    Square(Colour colour, Piece piece, int rank, int file) {

        if (!Board.validPosition(rank, file)) {
            // TODO throw exception
        }

        this.colour = colour;
        this.piece = piece;
        this.rank = rank;
        this.file = file;
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

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    /**
     * Draws the current square and any piece occupying that square.
     */
    public void draw() {
        String output;
        Piece piece = getPiece();
        String colourCode;
        
        if (piece != null) {
            output = (piece.getSymbol() + " ");
            colourCode = Graphics.mergeColours(piece.getColour().ansiString, getColour().ansiString);
        }
        else {
            output = ("  ");
            colourCode = getColour().ansiString;
        }
        System.out.print(colourCode + output + Graphics.ANSI_RESET_COLOUR);
    }
}