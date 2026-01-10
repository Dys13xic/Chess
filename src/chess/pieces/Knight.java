package chess.pieces;

public class Knight extends Piece {

    public Knight(Colour colour) {
        super(colour, Type.KNIGHT);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2658' : '\u265E';
    }

    @Override
    public boolean validMovementPattern(int currentRank, int currentFile) {
        // // TODO throw exception if board or targetSquare are null
        // Square sourceSquare = board.getPieceSquare(this);
        // int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        // int fileDifference =  targetSquare.getFile() - sourceSquare.getFile();

        // if ((Math.abs(rankDifference) != 2 && Math.abs(fileDifference) != 2) || (Math.abs(rankDifference) != 1 && Math.abs(fileDifference) != 1)) {
        //     return false;
        // }
        return true;
    }
}
