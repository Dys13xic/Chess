package chess.pieces;

public class Rook extends Piece {

    public Rook(Colour colour) {
        super(colour, Type.ROOK);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2656' : '\u265C';
    }

    @Override
    public boolean validMovementPattern(int currentRank, int currnetFile) {
        // // TODO throw exception if board or targetSquare are null
        // Square sourceSquare = board.getPieceSquare(this);
        // int rankDifference = targetSquare.getRank() - sourceSquare.getRank();
        // int fileDifference = targetSquare.getFile() - sourceSquare.getFile();

        // if (targetSquare == sourceSquare || (rankDifference != 0 && fileDifference != 0)) {
        //     return false;
        // }
        return true;

    }
}
