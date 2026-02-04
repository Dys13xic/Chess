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

    public abstract char getSymbol();

    protected ArrayList<PseudoLegalMove> getPseudoLegalMovesFromDelta(BoardView board, int[][] deltas) {
        ArrayList<PseudoLegalMove> moves = new ArrayList<PseudoLegalMove>();
        Coordinate source = board.getPieceCoordinate(this);

        for (int[] delta : deltas) {
            Coordinate target = new Coordinate(source.getRank() + delta[0], source.getFile() + delta[1]);
            if (!board.inBounds(target)) {
                break;
            }
            Piece targetPiece = board.getPieceAt(target);
            if (targetPiece != null && isFriendly(targetPiece)) {
                break;
            }
            PseudoLegalMove.Type moveType = (targetPiece == null) ? PseudoLegalMove.Type.QUIET : PseudoLegalMove.Type.CAPTURE;
            moves.add(new PseudoLegalMove(moveType, source, target,null));
        }
        
        return moves;
    }

    public abstract ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board);
}