package chess.pieces;
import chess.Board;
import chess.Square;

import java.util.ArrayList;

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

    public abstract boolean validMovementPattern(Board board, Square targetSquare);

    public ArrayList<Piece> obstructingPieces(Board board, Square targetSquare) {
        // TODO throw exception if board or targetSquare are null

        if(!validMovementPattern(board, targetSquare)) {
            // TODO throw exception
        }

        Square sourceSquare = board.getPieceSquare(this);
        int sourceRank = sourceSquare.getRank();
        int sourceFile = sourceSquare.getFile();
        int targetRank = targetSquare.getRank();
        int targetFile = targetSquare.getFile();

        int rankDifference = targetRank - sourceRank;
        int fileDifference =  targetFile - sourceFile;
        int rankDirection = (rankDifference > 0) ? 1 : -1;
        int fileDirection = (fileDifference > 0) ? 1 : -1;

        ArrayList<Piece> pieces = new ArrayList<Piece>();
        int rank = sourceRank + rankDirection;
        int file = sourceFile + fileDirection;

        while (rank != targetRank && file != targetFile) {
            Piece currentPiece = board.getSquareAt(rank, file).getPiece();
            if(currentPiece != null) {
                pieces.add(currentPiece);
            }
            if (rank != targetRank) {
                rank += rankDirection;
            }
            if (file != targetFile) {
                file += fileDirection;
            }
        }

        // If friendly piece exists on target square include in obstruction list.
        Piece targetPiece = targetSquare.getPiece();
        if(targetPiece != null && targetPiece.getColour() == getColour()) {
            pieces.add(targetPiece);
        }

        return pieces;
    }

    // public abstract ArrayList<Piece> obstructingPieces(Board board, Square targetSquare);
    //     // TODO throw exception if board or targetSquare are null
    //     Square sourceSquare = board.getPieceSquare(this);
    //     int sourceRank = sourceSquare.getRank();
    //     int sourceFile = sourceSquare.getFile();
        
    //     int targetRank = targetSquare.getRank();
    //     int targetFile = targetSquare.getFile();

    //     int rankDifference = targetRank - sourceRank;
    //     int fileDifference =  targetFile - sourceFile;

    //     int rankDirection = (rankDifference > 0) ? 1 : -1;
    //     int fileDirection = (fileDifference > 0) ? 1 : -1;

    //     if (sourceSquare == targetSquare) {
    //         // TODO throw exception
    //     }

    //     ArrayList<Piece> pieces = new ArrayList<Piece>();

    //     // Target square along piece's diagonal
    //     if (Math.abs(rankDifference) == Math.abs(fileDifference)) {

    //         for (int rank = sourceRank + rankDirection, file = sourceFile + fileDirection; (rank * rankDirection) <= (targetRank * rankDirection); rank += rankDirection, file += fileDirection) {
    //             Square currentSquare = board.getSquareAt(rank, file);
    //             Piece currentPiece = currentSquare.getPiece();
    //             if(currentPiece != null) {
    //                 pieces.add(currentPiece);
    //             }
    //         }
    //     }
    //     // Target square along piece's rank
    //     else if (rankDifference == 0) {
    //         for (int file = sourceFile + fileDirection; (file * fileDirection) <= (targetFile * fileDirection); file += fileDirection) {
    //             Square currentSquare = board.getSquareAt(sourceRank, file);
    //             Piece currentPiece = currentSquare.getPiece();
    //             if(currentPiece != null) {
    //                 pieces.add(currentPiece);
    //             }
    //         }
    //     }
    //     // Target square along piece's file
    //     else if (fileDifference == 0) {
    //         for (int rank = sourceRank + rankDirection; (rank * rankDirection) <= (targetRank * rankDirection); rank += rankDirection) {
    //             Square currentSquare = board.getSquareAt(rank, sourceFile);
    //             Piece currentPiece = currentSquare.getPiece();
    //             if(currentPiece != null) {
    //                 pieces.add(currentPiece);
    //             }
    //         }
    //     }
    //     // Target square is disconnected from piece's position
    //     else {
    //         Piece targetPiece = targetSquare.getPiece();
    //         if (targetPiece != null) {
    //             pieces.add(targetPiece);
    //         }
    //     }

    //     return pieces;
    // }

    // public abstract ArrayList<Square> legalMoves(Board board);
}