package chess.pieces;

import java.util.ArrayList;

import chess.BoardView;
import chess.Coordinate;
import chess.PseudoLegalMove;

public abstract class Piece {

    public enum Colour {
        WHITE("\033[38;5;0m"),
        BLACK("\033[38;5;0m");

        public final String ansiString;

        private Colour(String ansiString) {
            this.ansiString = ansiString;
        }
    }

    public enum Type {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }

    private Colour colour;
    private Type type;

    public Piece(Colour colour, Type type) {
        this.colour = colour;
        this.type = type;
    }

    public Colour getColour() {
        return colour;
    }

    public Type getType() {
        return type;
    }

    protected boolean isFriendly(Piece piece) {
        return this.colour == piece.colour;
    }

    private PseudoLegalMove buildPseudoLegalMove(BoardView board, Coordinate source, Coordinate target) {
        Piece targetPiece = board.getPieceAt(target);
        // Note: includes moves that explicitly capture the enemy king.
        if (targetPiece != null && isFriendly(targetPiece)) return null;
        return new PseudoLegalMove(targetPiece, source, target, null);
    }

    protected ArrayList<PseudoLegalMove> getPseudoLegalMovesFromVector(BoardView board, int[][] movementVectors) {
        ArrayList<PseudoLegalMove> moves = new ArrayList<PseudoLegalMove>();
        Coordinate source = board.getPieceCoordinate(this);
        for (int[] vector : movementVectors) {
            // TODO should I add a size check to the arraylist?
            int rankStep = vector[0];
            int fileStep = vector[1];
            Coordinate target = new Coordinate(source.getRank() - rankStep, source.getFile() - fileStep);

            while(board.inBounds(target)) {
                PseudoLegalMove move = buildPseudoLegalMove(board, source, target);
                if (move == null) break;
                moves.add(move);
                target = new Coordinate(target.getRank() - rankStep, target.getFile() - fileStep);
            }
        }
        return moves;
    }

    protected ArrayList<PseudoLegalMove> getPseudoLegalMovesFromDelta(BoardView board, int[][] deltas) {
        ArrayList<PseudoLegalMove> moves = new ArrayList<PseudoLegalMove>();
        Coordinate source = board.getPieceCoordinate(this);

        for (int[] delta : deltas) {
            Coordinate target = new Coordinate(source.getRank() + delta[0], source.getFile() + delta[1]);
            if (!board.inBounds(target)) break;
            PseudoLegalMove move = buildPseudoLegalMove(board, source, target);
            if (move == null) break;
            moves.add(move);
        }
        return moves;
    }

    public abstract char getSymbol();
    public abstract ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board);
}