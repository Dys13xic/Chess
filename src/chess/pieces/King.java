package chess.pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Square;

public class King extends Piece {

    private boolean inCheck;

    public King(Colour colour, boolean inCheck) {
        super(colour, Type.KING);
        this.inCheck = inCheck;
    }

    public boolean getInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2654' : '\u265A';
    }

    @Override
    public boolean validMovementPattern(Board board, Square targetSquare) {
        // TODO throw exception if board or targetSquare are null
        Square sourceSquare = board.getPieceSquare(this);
        int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        int fileDifference =  targetSquare.getFile() - sourceSquare.getFile();

        if (targetSquare == sourceSquare || Math.abs(rankDifference) > 1 || Math.abs(fileDifference) > 1) {
            return false;
        }
        return true;
    }

    // @Override
    // public ArrayList<Piece> obstructingPieces(Board board, Square targetSquare) {
    //     // TODO should this also cover castling?

    //     // TODO throw exception if board or targetSquare are null
    //     Square sourceSquare = board.getPieceSquare(this);

    //     int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
    //     int fileDifference =  targetSquare.getFile() - sourceSquare.getFile();

    //     if (targetSquare == sourceSquare || Math.abs(rankDifference) > 1 || Math.abs(fileDifference) > 1) {
    //         // Todo throw exception
    //     }

    //     ArrayList<Piece> pieces = new ArrayList<Piece>();
    //     Piece targetPiece = targetSquare.getPiece();
    //     if (targetPiece != null) {
    //         pieces.add(targetPiece);
    //     }

    //     return new ArrayList<Piece>();
    // }

    // @Override
    // public Square[] legalMoves(Board board) {
    //     // Are friendly pieces limiting diagonal/vertical/horizontal movement?

    //     // Are enemy pieces attacking squares?
    // }
    
}
