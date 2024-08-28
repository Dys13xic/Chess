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
import java.util.Arrays;
import java.util.Collections;

public class Board {

    public static final int RANK_COUNT = 8;
    public static final int FILE_COUNT = 8;
    
    private Square[][] grid;
    private Piece.Colour activePlayer;
    private int halfMoveClock;
    private int moveCount;

    public Board() {
        grid = new Square[RANK_COUNT][FILE_COUNT];
        // Instantiate grid squares
        for (int rank = 0; rank < grid.length; rank++) {
            for (int file = 0; file < grid[rank].length; file++) {
                Square.Colour squareColour;
                if((rank + file) % 2 == 0) {
                    squareColour = Square.Colour.DARK;
                }
                else {
                    squareColour = Square.Colour.LIGHT;
                }
                grid[rank][file] = new Square(squareColour, null, rank, file);
            }
        }

        activePlayer = Piece.Colour.WHITE;
        halfMoveClock = 0;
        moveCount = 1;
    }

    public Board(String fen) {
        this();
        loadFen(fen);
    }

    public Square[][] getGrid() {
        return grid;
    }

    public Square getSquareAt(int rank, int file) {
        if (!validPosition(rank, file)) {
            // TODO throw exception
        }

        Square[][] tempGrid = getGrid();
        return tempGrid[rank][file];
    }

    public Square getPieceSquare(Piece targetPiece) {
        Square[][] tempGrid = getGrid();
        
        for (int rank = 0; rank < tempGrid.length; rank++) {
            for (int file = 0; file < tempGrid[rank].length; file++) {
                Square currentSquare = tempGrid[rank][file];
                if (currentSquare.getPiece() == targetPiece) {
                    return tempGrid[rank][file];
                }
            }
        }

        return null;
    }

    public ArrayList<Piece> getPieces() {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        
        for (int rank = 0; rank < RANK_COUNT; rank++) {
            for (int file = 0; file < FILE_COUNT; file++) {
                Piece currentPiece = grid[rank][file].getPiece();

                if (currentPiece != null) {
                    pieces.add(currentPiece);
                }
            }
        }
        return pieces;
    }

    // TODO should there be a field to filter by rank and column?
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

    public Piece.Colour getActivePlayer() {
        return activePlayer;
    }

    int getHalfMoveClock() {
        return halfMoveClock;
    }

    int getMoveCount() {
        return moveCount;
    }

    private void insertPiece(Square targetSquare, Piece newPiece) {
        targetSquare.setPiece(newPiece);
    }

    /**
     * Set the board position to match the specified FEN
     */
    private void loadFen(String fen) {      // TODO clean up method
        // Validate FEN structure
        if (!fen.matches("^([rnbqkpRNBQKP1-8]{1,8}\\/){7}[rnbqkpRNBQKP1-8]{1,8}\\s[w,b]\\s([kqKQ]{1,4}|-)\\s(([a-h][3,6])|-)\\s\\d{1,3}\\s\\d{1,3}$"))
            // TODO throw error
            System.out.println("placeholder");

        String[] fields = fen.split(" ");

        // 1 - Position
        String[] position = fields[0].split("/");
        Collections.reverse(Arrays.asList(position));           // TODO should I replace this with more handwritten code?

        for (int rank = 0; rank < position.length; rank++) {
            int rankSquareCount = 0;
            for (int file = 0; file < position[rank].length(); file++) {
                Piece currentPiece;
                Piece.Colour colour = Piece.Colour.WHITE;
                switch(position[rank].charAt(file)) {
                    case 'p':
                        colour = Piece.Colour.BLACK;
                    case 'P':
                        currentPiece = new Pawn(colour, false); // TODO actually determine enpassant
                        insertPiece(grid[rank][file], currentPiece);
                        rankSquareCount++;
                        break;

                    case 'r':
                        colour = Piece.Colour.BLACK;
                    case 'R':
                        currentPiece = new Rook(colour);
                        insertPiece(grid[rank][file], currentPiece);
                        rankSquareCount++;
                        break;

                    case 'n':
                        colour = Piece.Colour.BLACK;
                    case 'N':
                        currentPiece = new Knight(colour);
                        insertPiece(grid[rank][file], currentPiece);
                        rankSquareCount++;
                        break;

                    case 'b':
                        colour = Piece.Colour.BLACK;
                    case 'B':
                        currentPiece = new Bishop(colour);
                        insertPiece(grid[rank][file], currentPiece);
                        rankSquareCount++;
                        break;

                    case 'q':
                        colour = Piece.Colour.BLACK;
                    case 'Q':
                        currentPiece = new Queen(colour);
                        insertPiece(grid[rank][file], currentPiece);
                        rankSquareCount++;
                        break;

                    case 'k':
                        colour = Piece.Colour.BLACK;
                    case 'K':
                        currentPiece = new King(colour, false); // TODO determine if in check
                        insertPiece(grid[rank][file], currentPiece);
                        rankSquareCount++;
                        break;

                    default:
                        char character = position[rank].charAt(file);
                        if (character < '0' || character > '9') {
                            // TODO throw error
                        }
                        int emptySquares = character - '0';
                        
                        if (file + emptySquares < FILE_COUNT) {
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
            if (rankSquareCount != RANK_COUNT) {
                // TODO  Throw exception
            }
        }

        // 1 - Active Colour
        activePlayer = fields[1].equals("w") ? Piece.Colour.WHITE : Piece.Colour.BLACK;

        // TODO 3 - Castle Rights
        // If king has moved or rook has moved Castle Rights must accurately reflect that.

        // Ensure there is only one king for each side

        // TODO 4 - En Passant

        // 5 & 6 - Move Count & Half Move Clock
        halfMoveClock = Integer.parseInt(fields[4]);
        moveCount = Integer.parseInt(fields[5]);

    }

    /**
     * Draws the chessboard's current state.
     */
    public void draw() {        // TODO add activePlayer parameter and allow printing from white or black perspective
        Graphics.clearDrawing();
        for (int rank = grid.length - 1; rank >= 0; rank--) {
            System.out.print((rank + 1) + " ");
            drawRank(grid[rank]);
        }
        System.out.println("  ＡＢＣＤＥＦＧＨ");
    }

    /**
     * Draws the input chessboard rank.
     * @param rank
     */
    private void drawRank(Square[] rank) {
        for (int file = 0; file < rank.length; file++) {
            rank[file].draw();
        }
        System.out.print("\n");
    }

/**
 * Determine's whether the input rank and file are within the chessboard's bounds.
 * @param int rank
 * @param file file
 * @return valid
 */
    public static boolean validPosition(int rank, int file) {
        boolean valid = false;
        if((rank >= 0 && rank < RANK_COUNT) && (file >= 0 && file < FILE_COUNT)) {
            valid = true;
        }
        return valid;
    }

}
