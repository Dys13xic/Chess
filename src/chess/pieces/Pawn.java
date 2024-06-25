package chess.pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Square;

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
    public ArrayList<Piece> obstructingPieces(Board board, Square targetSquare) {
        // TODO throw exception if board or targetSquare are null

        // TODO handle double move
        // TODO handle capture and en-passant capture?
        Square sourceSquare = board.getPieceSquare(this);
        int sourceRank = sourceSquare.getRank();
        int sourceFile = sourceSquare.getFile();

        int targetRank = targetSquare.getRank();
        int targetFile = targetSquare.getFile();

        int rankDifference = targetRank - sourceRank;
        int fileDifference =  targetFile - sourceFile;
    }
    
    // @Override
    // public Square[] legalMoves(Board board) {
    //     // King in check?
    //     // -> If yes
    //     // ---> can piece capture attacker
    //     // ---> can piece block attacker

    //     // Is piece pinned to king?
        
    //     // Is the space ahead free?

    //     // Are there enemy pieces to the diagonal?

    //     // Is there an en passant target to the side?
        
    // }

    // public void move(Board board, );
}
