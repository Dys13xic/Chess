package chess.pieces;

public class Pawn extends Piece {
    private boolean enPassantTarget;

    public Pawn(Colour colour, boolean enPassantTarget) {
        super(colour, Type.PAWN);
        this.enPassantTarget = enPassantTarget;
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2659' : '\u265F';
    }

    @Override
    public boolean validMovementPattern(int currentRank, int currentFile) {
        // // TODO throw exception if board or targetSquare are null
        // Square sourceSquare = board.getPieceSquare(this);
        // int advanceIncrement = (sourceSquare.getPiece().getColour() == Colour.WHITE) ? 1 : -1;
        // int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        // int fileDifference =  targetSquare.getFile() - sourceSquare.getFile();

        // if (rankDifference != advanceIncrement || (fileDifference >= -1 && fileDifference <= 1)) {
        //     return false;
        // }
        return true;
    }
}
