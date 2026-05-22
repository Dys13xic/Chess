package chess;
import java.util.ArrayList;

import chess.pieces.Piece;
import chess.positionformats.Fen;
import chess.positionformats.PositionFormats;

public class Position {
    private Board board;
    private int moveCount;
    private int halfMoveClock;
    private CastleRights[] castleRights;
    private Piece.Colour activePlayer;

    enum CastleRights {
        KINGSIDE,
        QUEENSIDE,
        BOTH;
    }

    public Position() {
        this(new Fen(Fen.STANDARD_POSITION));
    }

    public Position(PositionFormats format) {
        board = new Board();

        format.loadPosition(board);
        moveCount = format.getMoves().size();
        halfMoveClock = format.getHalfMoveClock();
        activePlayer = format.getActivePlayer();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getHalfMoveClock() {
        return halfMoveClock;
    }

    public Piece.Colour getActivePlayer() {
        return activePlayer;
    }

    private CastleRights getCastleRights(Piece.Colour player) {
        return this.castleRights[player.index];
    }

    private void removeCastleRights(Piece.Colour player, CastleRights side) {
        if (side == null) return;
        
        CastleRights current = this.getCastleRights(player);
        CastleRights updated = current;

        if (current == CastleRights.BOTH) {
            if (side == CastleRights.KINGSIDE) updated = CastleRights.QUEENSIDE;
            else if (side == CastleRights.QUEENSIDE) updated = CastleRights.KINGSIDE;
            else updated = null;
        }
        else if (current == side) {
            updated = null;
        }

        this.castleRights[player.index] = updated;
    }

    public void draw() {
        Graphics.drawBoard(board);
        // TODO add any other visuals (i.e. pieces captured, relative piece value, past moves, etc.)
    }
}