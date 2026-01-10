package chess.pieces;

import java.lang.Math;

public class Bishop extends Piece {

    public Bishop(Colour colour) {
        super(colour, Type.BISHOP);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2657' : '\u265D';
    }

    @Override
    public boolean validMovementPattern(int currentRank, int currentFile) {
        // // TODO throw exception if board or targetSquare are null
        // Square sourceSquare = board.getPieceSquare(this);
        // int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        // int fileDifference = targetSquare.getFile() - sourceSquare.getFile();

        // if (targetSquare == sourceSquare || Math.abs(rankDifference) != Math.abs(fileDifference)) {
        //     return false;
        // }
        return true;
    }
}
