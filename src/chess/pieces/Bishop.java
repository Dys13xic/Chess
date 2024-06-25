package chess.pieces;

import java.util.ArrayList;
import java.lang.Math;

import chess.Board;
import chess.Square;

public class Bishop extends Piece {

    public Bishop(Colour colour) {
        super(colour, Type.BISHOP);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2657' : '\u265D';
    }

    public ArrayList<Piece> obstructingPieces(Board board, Square targetSquare) {
        // TODO throw exception if board or targetSquare are null
        Square sourceSquare = board.getPieceSquare(this);
        int sourceRank = sourceSquare.getRank();
        int sourceFile = sourceSquare.getFile();
        
        int targetRank = targetSquare.getRank();
        int targetFile = targetSquare.getFile();

        int rankDifference = targetRank - sourceRank;
        int fileDifference =  targetFile - sourceFile;

        if (Math.abs(rankDifference) != Math.abs(fileDifference)) {
            // Todo throw exception
        }


        // TODO rename to rank and file increment?
        int rankBias = (rankDifference > 0) ? 1 : -1;
        int fileBias = (fileDifference > 0) ? 1 : -1;

        ArrayList<Piece> pieces = new ArrayList<Piece>();

        for (int rank = sourceRank + rankBias, file = sourceFile + fileBias; (rank * rankBias) <= (targetRank * rankBias); rank += rankBias, file += fileBias) {
            Square currentSquare = board.getSquareAt(rank, file);
            Piece currentPiece = currentSquare.getPiece();
            if(currentPiece != null) {
                pieces.add(currentPiece);
            }
        }
        return pieces;
    }

    // @Override
    // public Square[] legalMoves(Board board) {
    //     // King in check?
    //     // -> If yes
    //     // ---> can piece capture attacker
    //     // ---> can piece block attacker

    //     // Is piece pinned to king?
        
    //     // Are friendly pieces limiting diagonal movement?
    //     // Are capturable enemy pieces limiting diagonal movement?

    //     // Check diagonally to the top-left, bottom-right, top-right, bottom left.
    //     int tempRank = this.getRank();
    //     int tempFile = this.getFile();

    //     for (r = this.getRank(), f = this.getFile(); r < 8 && ; r++) {

    //     }
        
    // }
    
}
