package chess.pieces;

import java.util.ArrayList;

import chess.BoardView;
import chess.Coordinate;
import chess.PseudoLegalMove;

public class Knight extends Piece {

    public Knight(Colour colour) {
        super(colour, Type.KNIGHT);
    }

    @Override
    public char getSymbol() {
        return getColour() == Colour.WHITE ? '\u2658' : '\u265E';
    }

    @Override
    public ArrayList<PseudoLegalMove> getPseudoLegalMoves(BoardView board) {
        ArrayList<PseudoLegalMove> moves = new ArrayList<PseudoLegalMove>();
        Coordinate source = board.getPieceCoordinate(this);

        int[][] deltas = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};

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
}