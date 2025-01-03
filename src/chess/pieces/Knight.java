package chess.pieces;

import chess.Board;
import chess.Square;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(Colour colour) {
        super(colour, Type.KNIGHT);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2658' : '\u265E';
    }

    @Override
    public boolean validMovementPattern(Board board, Square targetSquare) {
        // TODO throw exception if board or targetSquare are null
        Square sourceSquare = board.getPieceSquare(this);
        int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        int fileDifference =  targetSquare.getFile() - sourceSquare.getFile();

        if ((Math.abs(rankDifference) != 2 && Math.abs(fileDifference) != 2) || (Math.abs(rankDifference) != 1 && Math.abs(fileDifference) != 1)) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Piece> obstructingPieces(Board board, Square targetSquare) {
        // TODO throw exception if board or targetSquare are null
        
        if(!validMovementPattern(board, targetSquare)) {
            // TODO throw exception
        }

        ArrayList<Piece> pieces = new ArrayList<Piece>();
        Piece targetPiece = targetSquare.getPiece();
        if (targetPiece != null && targetPiece.getColour() == getColour()) {
            pieces.add(targetPiece);
        }

        return new ArrayList<Piece>();
    }

    // @Override
    // public ArrayList<Square> legalMoves(Board board) {
    //     ArrayList<Square> validSquares = new ArrayList<Square>();
    //     int[][] potentialOffsets = {
    //                                     {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
    //                                     {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    //                                 };

    //     for (int i = 0; i < potentialOffsets.length; i++) {
    //         int tempRank = getRank() + potentialOffsets[i][0];
    //         int tempFile = getFile() + potentialOffsets[i][1];

    //         if ((tempRank >= 0 && tempRank < 8) && (tempFile >= 0 && tempFile < 8)) {
    //             Square tempSquare = board.getGrid()[tempRank][tempFile];



    //             // Friendly pieces restricting movement?
    //             if (this.getColour() != tempSquare.getPiece().getColour()) {

    //             }
    //         }
    //     }
    //     // Squares of movement

    //     ArrayList<Square> temp = new ArrayList<Square>();


    //     // King in check?
    //     // -> If yes
    //     // ---> can piece capture attacker
    //     // ---> can piece block attacker

    //     // Is piece pinned to king?
        
    //     // Are friendly pieces limiting movement?
    // }
  
}
