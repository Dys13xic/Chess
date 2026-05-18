package chess;
import chess.Board.Square;
import chess.pieces.Piece;

public class Graphics {

    public static final String ANSI_ESCAPE = "\003[";
    public static final String ANSI_RESET_COLOUR = "\u001B[0m";

    /**
     * Clears terminal screen and returns cursor to top-left using ANSI escape codes
     */
    public static void clearDrawing() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String mergeColours(String foreground, String background) {
        if (foreground == null || background == null) {  // TODO add regex check for formatting
            throw new IllegalArgumentException();
        }
        // Remove early terminating character
        foreground = foreground.substring(0, foreground.length() - 1);
        // Remove duplicate ANSI escape
        background = background.substring(ANSI_ESCAPE.length());

        return (foreground + ';' + background);
    }

    /**
     * Draws the chessboard's current state.
     */
    public static void drawBoard(Board chessBoard) {        // TODO add activePlayer parameter and allow printing from white or black perspective
        clearDrawing();
        for (int rank = Board.RANK_COUNT - 1; rank >= 0; rank--) {
            System.out.print((rank + 1) + " ");
            for (int file = 0; file < Board.FILE_COUNT; file++) {
                drawSquare(rank, file, chessBoard.getPieceAt(rank, file));
            }
            System.out.print("\n");
        }
        System.out.println("  ＡＢＣＤＥＦＧＨ");
    }

    public static void drawSquare(int rank, int file, Piece piece) {
        String output;
        Square squareColour;
        String colourCode;
        
        if((rank + file) % 2 == 0) {
            squareColour = Square.DARK;
        }
        else {
            squareColour = Square.LIGHT;
        }

        if (piece != null) {
            output = (piece.getSymbol() + " ");
            colourCode = mergeColours(piece.getColour().ansiString, squareColour.ansiString);
        }
        else {
            output = ("  ");
            colourCode = squareColour.ansiString;
        }
        System.out.print(colourCode + output + ANSI_RESET_COLOUR);
    }

}
