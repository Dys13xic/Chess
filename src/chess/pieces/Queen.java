package chess.pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Square;

public class Queen extends Piece {

    public Queen(Colour colour) {
        super(colour, Type.QUEEN);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2655' : '\u265B';
    }
    @Override
    public boolean validMovementPattern(Board board, Square targetSquare) {
        // TODO throw exception if board or targetSquare are null
        Square sourceSquare = board.getPieceSquare(this);
        int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        int fileDifference = targetSquare.getFile() - sourceSquare.getFile();

        if (targetSquare == sourceSquare || ((rankDifference != 0 && fileDifference != 0) && Math.abs(rankDifference) != Math.abs(fileDifference))) {
            return false;
        }
        return true;

    }

    // public ArrayList<Piece> obstructingPieces(Board board, Square targetSquare) {
    //     // TODO throw exception if board or targetSquare are null

    //     if(!validMovementPattern(board, targetSquare)) {
    //         // TODO throw exception
    //     }

    //     Square sourceSquare = board.getPieceSquare(this);
    //     int sourceRank = sourceSquare.getRank();
    //     int sourceFile = sourceSquare.getFile();
        
    //     int targetRank = targetSquare.getRank();
    //     int targetFile = targetSquare.getFile();

    //     int rankDifference = targetRank - sourceRank;
    //     int fileDifference =  targetFile - sourceFile;

    //     int rankDirection = (rankDifference > 0) ? 1 : -1;
    //     int fileDirection = (fileDifference > 0) ? 1 : -1;

    //     ArrayList<Piece> pieces = new ArrayList<Piece>();

    //     int rank = sourceRank + rankDirection;
    //     int file = sourceFile + fileDirection;

    //     while (rank != targetRank && file != targetFile) {
    //         Piece currentPiece = board.getSquareAt(rank, file).getPiece();
    //         if(currentPiece != null) {
    //             pieces.add(currentPiece);
    //         }

    //         if (rank != targetRank) {
    //             rank += rankDirection;
    //         }
    //         if (file != targetFile) {
    //             file += fileDirection;
    //         }
    //     }

    //     // If friendly piece exists on target square include in obstruction list.
    //     Piece targetPiece = targetSquare.getPiece();
    //     if(targetPiece != null && targetPiece.getColour() == getColour()) {
    //         pieces.add(targetPiece);
    //     }

    //     return pieces;
    // }

    // @Override
    // public Square[] legalMoves(Board board) {
    //     // King in check?
    //     // -> If yes
    //     // ---> can piece capture attacker
    //     // ---> can piece block attacker

    //     // Is piece pinned to king?
        
    //     // Are friendly pieces limiting diagonal/vertical/horizontal movement?

    //     // Are capturable enemy pieces limiting diagonal/vertical/horizontal movement?
    // }
    
}
