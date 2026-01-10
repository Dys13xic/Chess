package chess.pieces;

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
    public boolean validMovementPattern(int currentRank, int currentFile) {
        // // TODO throw exception if board or targetSquare are null
        // Square sourceSquare = board.getPieceSquare(this);
        // int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        // int fileDifference =  targetSquare.getFile() - sourceSquare.getFile();

        // if (targetSquare == sourceSquare || Math.abs(rankDifference) > 1 || Math.abs(fileDifference) > 1) {
        //     return false;
        // }
        return true;
    }
}
