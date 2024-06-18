package chess;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.pieces.Piece.Type;
import java.util.ArrayList;

public class Board {

    // TODO place into seperate class?
    public static final String RESET_COLOUR = "\u001B[0m";
    
    Square[][] grid;
    ArrayList<Piece> pieces;
    Piece.Colour activePlayer;
    int halfMoveClock;
    int move;

    public Board() {
        grid = new Square[8][8];    // TODO replace magic numbers with named constant
        // Instantiate grid squares
        for (int row = 0; row < grid.length; row++) {
            for (int file = 0; file < grid[row].length; file++) {
                Square.Colour squareColour;
                if((row + file) % 2 == 0) {
                    squareColour = Square.Colour.DARK;
                }
                else {
                    squareColour = Square.Colour.LIGHT;
                }
                grid[row][file] = new Square(squareColour, null);
            }
        }

        pieces = new ArrayList<Piece>();
        activePlayer = Piece.Colour.WHITE;
        halfMoveClock = 0;
        move = 1;
    }

    public Board(String fen) {
        this();
        this.loadFen(fen);
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    // TODO should there be a field to filter by row and column?
    public ArrayList<Piece> getFilteredPieces(Piece.Colour pieceColour, Type type) {

        ArrayList<Piece> pieceList = getPieces();
        ArrayList<Piece> filteredPieceList = new ArrayList<Piece>();
        Piece currentPiece;

        for (int i = 0; i < pieceList.size(); i++) {
            currentPiece = pieceList.get(i);

            // If colour/type is null, do not filter based on that object property.
            if(pieceColour != null && pieceColour != currentPiece.getColour()) {
                continue;
            }
            if(type != null && type != currentPiece.getType()) {
                continue;
            }

            filteredPieceList.add(currentPiece);
        }
        return filteredPieceList;
    }

    /**
     * Set the board position to match the specified FEN
     */
    private void loadFen(String fen) {      // TODO clean up method
        // Validate FEN structure
        if(!fen.matches("^([rnbqkpRNBQKP1-8]{1,8}\\/){7}[rnbqkpRNBQKP1-8]{1,8}\\s[w,b]\\s([kqKQ]{1,4}|-)\\s(([a-h][3,6])|-)\\s\\d{1,3}\\s\\d{1,3}$"))
            // TODO throw error
            System.out.println("placeholder");

        String[] fields = fen.split(" ");

        // 1 - Position
        String[] position = fields[0].split("/");

        for (int rank = 0; rank < position.length; rank++) {
            int rankSquareCount = 0;
            for (int file = 0; file < position[rank].length(); file++) {
                Piece currentPiece;
                Piece.Colour colour = Piece.Colour.WHITE;
                switch(position[rank].charAt(file)) {
                    case 'p':
                        colour = Piece.Colour.BLACK;
                    case 'P':
                        currentPiece = new Pawn(colour, rank, file, false); // TODO actually determine enpassant
                        grid[rank][file].setPiece(currentPiece);
                        rankSquareCount++;
                        break;

                    case 'r':
                        colour = Piece.Colour.BLACK;
                    case 'R':
                        currentPiece = new Rook(colour, rank, file);
                        grid[rank][file].setPiece(currentPiece);
                        rankSquareCount++;
                        break;

                    case 'n':
                        colour = Piece.Colour.BLACK;
                    case 'N':
                        currentPiece = new Knight(colour, rank, file);
                        grid[rank][file].setPiece(currentPiece);
                        rankSquareCount++;
                        break;

                    case 'b':
                        colour = Piece.Colour.BLACK;
                    case 'B':
                        currentPiece = new Bishop(colour, rank, file);
                        grid[rank][file].setPiece(currentPiece);
                        rankSquareCount++;
                        break;

                    case 'q':
                        colour = Piece.Colour.BLACK;
                    case 'Q':
                        currentPiece = new Queen(colour, rank, file);
                        grid[rank][file].setPiece(currentPiece);
                        rankSquareCount++;
                        break;

                    case 'k':
                        colour = Piece.Colour.BLACK;
                    case 'K':
                        currentPiece = new King(colour, rank, file, false); // TODO determine if in check
                        grid[rank][file].setPiece(currentPiece);
                        rankSquareCount++;
                        break;

                    default:
                        char character = position[rank].charAt(file);
                        if (character < '0' || character > '9') {
                            // TODO throw error
                        }
                        int emptySquares = character - '0';
                        
                        if (file + emptySquares < 8) {      // TODO replace magic number
                            for (int i = 0; i < emptySquares; i++) {
                                grid[rank][file + i] = null;
                                rankSquareCount++;
                            }
                        }
                        else {
                            // Throw exception
                        }

                        break;
                }
            }
            if (rankSquareCount != 8) {  // TODO replace magic number
                // TODO  Throw exception
            }
        }

        // 1 - Active Colour
        activePlayer = fields[1].equals("w") ? Piece.Colour.WHITE : Piece.Colour.BLACK;

        // TODO 3 - Castle Rights
        // If king has moved or rook has moved Castle Rights must accurately reflect that.

        // Ensure there is only one king for each side

        // TODO 4 - En Passant

        // 5 & 6 - Move & Half Move Clock
        halfMoveClock = Integer.parseInt(fields[4]);
        move = Integer.parseInt(fields[5]);

    }

    /**
     * Clears terminal screen and returns cursor to top-left using ANSI escape codes
     */
    private void clearDrawing() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Draws the chessboard's current state from top to bottom rank.
     */
    public void draw() {
        this.clearDrawing();
        for (int rank = grid.length - 1; rank >= 0; rank--) {
            drawRank(grid[rank]);
        }
    }

    /**
     * Draws the input chessboard rank.
     * @param rank
     */
    private void drawRank(Square[] rank) {
        for (int file = 0; file < rank.length; file++) {
            drawSquare(rank[file]);
        }
        System.out.print("\n");
    }

    /**
     * Draws the input chessboard square and any piece occupying that square.
     * @param square
     */
    private void drawSquare(Square square) {
        String output;
        Piece piece = square.getPiece();
        String colourCode;
        
        // Draw piece if one exists
        if (piece != null) {
            output = (piece.getSymbol() + " ");
            colourCode = mergeColours(piece.getColour().ansiString, square.getColour().ansiString);
        }
        else {
            output = ("  ");
            colourCode = square.getColour().ansiString;
        }
        System.out.print(colourCode + output + RESET_COLOUR);
    }

    private String mergeColours(String foreground, String background) {
        if(foreground == null || background == null) {  // TODO add regex check for formatting
            // TODO throw exception
        }
        // Remove ANSI escape code terminating character 'm'
        foreground = foreground.substring(0, foreground.length() -1 );
        // Extract colour defining portion of ANSI escape code, i.e. remove "\033["
        background = background.substring(2);

        return (foreground + ';' + background);
    }
}
