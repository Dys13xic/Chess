import pieces.Piece;

public class Board {
    Piece[][] currentBoard;
    Piece[][] futureBoard;

    /**
     * Set the board position to match the specified FEN
     */
    private void loadFen(Board board, String fen) {

        // TODO verify board is not null
        // TODO validate fen format?

        int r = 0;
        int c = 0;
        char currentChar;

        for (int i = 0; i < fen.length(); i++) {
            currentChar = fen.charAt(i);

            switch(currentChar) {
                case 'p':
                
                case 'r':

                case 'n':

                case 'b':

                case 'q':

                case 'k':

                default:


            }

        }
    }

    public Board() {

    }

    public Board(String fen) {
        if()
    }

}
