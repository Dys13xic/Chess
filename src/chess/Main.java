package chess;

import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Board chessboard = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        chessboard.draw();
    }
}
