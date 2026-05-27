package chess;

import chess.pieces.Pawn;
import chess.pieces.Piece;

import java.util.ArrayList;

public class Board implements BoardView {
    public static final int RANK_COUNT = 8;
    public static final int FILE_COUNT = 8;

    public static final int BLACK_BACK_RANK = RANK_COUNT - 1;
    public static final int BLACK_PAWN_RANK = RANK_COUNT - 2;
    public static final int WHITE_BACK_RANK = 0;
    public static final int WHITE_PAWN_RANK = 1;
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

    @Override
    public Coordinate getPieceCoordinate(Piece piece) {
        for (int rank = 0; rank < RANK_COUNT; rank++) {
            for (int file = 0; file < FILE_COUNT; file++) {
                if (grid[rank][file] == piece)
                    return new Coordinate(rank, file);
            }
        }
        return null;
    }

    public Piece getPieceAt(int rank, int file) {
        return grid[rank][file];
    }

    public Piece getPieceAt(Coordinate coordinate) {
        return getPieceAt(coordinate.getRank(), coordinate.getFile());
    }

    public ArrayList<Piece> getPieces() {
        return getPieces(null);
    }

    public ArrayList<Piece> getPieces(Piece.Colour colour) {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        
        for (int rank = 0; rank < RANK_COUNT; rank++) {
            for (int file = 0; file < FILE_COUNT; file++) {
                Piece currentPiece = grid[rank][file];

                boolean emptySquare = currentPiece == null;
                boolean wrongColour = colour != null && colour != currentPiece.getColour();
                if (emptySquare || wrongColour) continue;

                pieces.add(currentPiece);
            }
        }
        return pieces;
    }

    // TODO should there be a field to filter by rank and column?
    public <T extends Piece> ArrayList<T> getPiecesOfType(Class<T> type, Piece.Colour colour) {
        ArrayList<Piece> pieceList = getPieces();
        ArrayList<T> filteredPieceList = new ArrayList<T>();
        Piece currentPiece;

        for (int i = 0; i < pieceList.size(); i++) {
            currentPiece = pieceList.get(i);

            // If colour is null, do not filter based on that object property.
            if(colour != null && colour != currentPiece.getColour()) {
                continue;
            }
            if(!type.isInstance(currentPiece)) {
                continue;
            }

            filteredPieceList.add(type.cast(currentPiece));
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
    public boolean inBounds(int rank, int file) {
        if((rank >= 0 && rank < getRankCount()) && (file >= 0 && file < getFileCount())) {
            return true;
        }
        return false;
    }

    public boolean inBounds(Coordinate coordinate) {
        return inBounds(coordinate.getRank(), coordinate.getFile());
    }

    public boolean isPromotionSquare(Piece.Colour colour, Coordinate target) {
        if (colour == Piece.Colour.WHITE && target.getRank() == WHITE_BACK_RANK) {
            return true;
        }
        if (colour == Piece.Colour.BLACK && target.getRank() == BLACK_BACK_RANK) {
            return true;
        }
        return false;
    }

    public boolean isStartingRank(Piece.Colour colour, Piece.Type type, Coordinate target) {
        if (colour == Piece.Colour.WHITE) {
            int startingRank = (type == Piece.Type.PAWN) ? WHITE_PAWN_RANK : WHITE_BACK_RANK;
            return target.getRank() == startingRank;
        }
        if (colour == Piece.Colour.BLACK) {
            int startingRank = (type == Piece.Type.PAWN) ? BLACK_PAWN_RANK : BLACK_BACK_RANK;
            return target.getRank() == startingRank;
        }
        return false;
    }

    public boolean onStartingRank(Piece piece) {
        return isStartingRank(piece.getColour(), piece.getType(), getPieceCoordinate(piece));
    }

    public Piece.Colour getHalf(Coordinate target) {
        return target.getRank() < (RANK_COUNT / 2) ? Piece.Colour.WHITE : Piece.Colour.BLACK;
    }

    public void clear() {
        grid = new Piece[getRankCount()][getFileCount()];
    }
}