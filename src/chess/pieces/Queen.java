package chess.pieces;

public class Queen extends Piece {

    public Queen(Colour colour) {
        super(colour, Type.QUEEN);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2655' : '\u265B';
    }
    @Override
    public boolean validMovementPattern(int currentRank, int currentFile) {
        // // TODO throw exception if board or targetSquare are null
        // Square sourceSquare = board.getPieceSquare(this);
        // int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        // int fileDifference = targetSquare.getFile() - sourceSquare.getFile();

        // if (targetSquare == sourceSquare || ((rankDifference != 0 && fileDifference != 0) && Math.abs(rankDifference) != Math.abs(fileDifference))) {
        //     return false;
        // }
        return true;

    }
}
