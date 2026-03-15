package chess.pieces;

import java.util.ArrayList;

import chess.Coordinate;
import chess.BoardView;
import chess.PseudoLegalMove;

public class Pawn extends Piece {

    private static final Piece.Type[] validPromotions = {
        Type.ROOK,
        Type.KNIGHT,
        Type.BISHOP,
        Type.QUEEN
    };

    private boolean enPassantTarget;

    public Pawn(Colour colour, boolean enPassantTarget) {
        super(colour, Type.PAWN);
        this.enPassantTarget = enPassantTarget;
    }

    public static Piece.Type[] getValidPromotions() {
        return validPromotions;
    }

    public boolean validEnPassantTarget() {
        return enPassantTarget;
    }

    public void setEnPassantTarget(boolean value) {
        enPassantTarget = value;
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2659' : '\u265F';
    }

    @Override
    public ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board) {
        int directionOfTravel = getColour() == Piece.Colour.WHITE ? 1 : -1;
        Coordinate source = board.getPieceCoordinate(this);
        ArrayList<PseudoLegalMove> moves = new ArrayList<PseudoLegalMove>();
        ArrayList<PseudoLegalMove> tempMoves = new ArrayList<PseudoLegalMove>();

        // Forward movement.
        int movementLimit = board.onStartingRank(this) ? 1 : 2;
        int[][] movementVectors = {{directionOfTravel, 0, movementLimit}};
        tempMoves.addAll(getPseudoLegalMovesFromVector(board, movementVectors));

        // Diagonal captures.
        int[][] deltas = {{directionOfTravel, 1}, {directionOfTravel, -1}};
        tempMoves.addAll(getPseudoLegalMovesFromDelta(board, deltas));

        // En passant captures. (Never includes promotion).
        deltas = new int[][]{{0, 1}, {0, -1}};
        for (int[] delta : deltas) {
            Coordinate tempTarget = new Coordinate(source.getRank() + delta[0], source.getFile() + delta[1]);
            Coordinate finalTarget = new Coordinate(tempTarget.getRank() + directionOfTravel, tempTarget.getFile());
            
            if (!board.inBounds(tempTarget) || !board.inBounds(finalTarget)) continue;
            Piece targetPiece = board.getPieceAt(tempTarget);
            
            if (targetPiece == null ||
                targetPiece.isFriendly(this) ||
                board.getPieceAt(finalTarget) != null
            ) continue;

            if (Pawn.class.isInstance(targetPiece)) {
                Pawn targetPawn = Pawn.class.cast(targetPiece);
                if (targetPawn.validEnPassantTarget()) {
                    moves.add(new PseudoLegalMove(targetPiece, source, finalTarget, null));
                }
            }
        }

        // Expand promotion moves
        for (PseudoLegalMove move : tempMoves) {
            if (board.isPromotionSquare(getColour(), move.getTarget())) {
                for (Piece.Type promotion : getValidPromotions()) {
                    moves.add(new PseudoLegalMove(move, promotion));
                }
            }
            else {
                moves.add(move);
            }
        }

        return moves;
    }
}