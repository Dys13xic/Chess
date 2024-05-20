import pieces.Piece;
import pieces.Piece.Colour;

public class Board {
    Piece[][] squares;
    Colour activeColour;
    int move;
    int halfMoveClock; 

    /**
     * Set the board position to match the specified FEN
     */
    private boolean loadFen(Board board, String fen) {
        // Validate FEN structure
        if(board == null || !fen.matches("^([rnbqkpRNBQKP1-8]{1,8}\\/){7}[rnbqkpRNBQKP1-8]{1,8}\\s[w,b]\\s([kqKQ]{1,4}|-)\\s(([a-h][3,6])|-)\\s\\d{1,3}\\s\\d{1,3}$"))
            return false;

        String[] fields = fen.split(" ");

        // TODO set all board squares to null? (i.e. clean the board before loading a new FEN)

        // Position
        String[] position = fields[0].split("/");

        for (int rank = 0; rank < position.length; rank++) {
            int numSquares = 0;
            for (int file = 0; file < position[rank].length(); file++) {
                switch(position[rank].charAt(file)) {

                }
            }
        }

        // Active Colour
        activeColour = fields[1].equals("w") ? Colour.WHITE : Colour.BLACK;

        // TODO Castle Rights
        // If king has moved or rook has moved Castle Rights must accurately reflect that.

        // TODO En Passant

        // Move & Half Move Clock
        halfMoveClock = Integer.parseInt(fields[4]);
        move = Integer.parseInt(fields[5]);

        return true;
    }

    public Board() {
        // Load starting position.
        loadFen(this, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

    }

    public Board(String fen) {
        loadFen(this, fen);
    }

}
