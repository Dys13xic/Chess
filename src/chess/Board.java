package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Type;

import java.util.ArrayList;

public class Board {
    public static final int RANK_COUNT = 8;
    public static final int FILE_COUNT = 8;
    private Piece[][] grid;

    public enum Square {
        LIGHT("\033[48;5;222m"),
        DARK("\033[48;5;94m");

        public final String ansiString;

        private Square(String ansiString) {
            this.ansiString = ansiString;
        }
    }

    public Board() {
        grid = new Piece[RANK_COUNT][FILE_COUNT];
    }

    public int getRankCount() {
        return RANK_COUNT;
    }

    public int getFileCount() {
        return FILE_COUNT;
    }

    public Piece getPieceAt(int rank, int file) {
        return grid[rank][file];
    }

    public ArrayList<Piece> getPieces() {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        
        for (int rank = 0; rank < RANK_COUNT; rank++) {
            for (int file = 0; file < FILE_COUNT; file++) {
                Piece currentPiece = grid[rank][file];

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

    public void insertPiece(int rank, int file, Piece newPiece) {
        this.grid[rank][file] = newPiece;
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
