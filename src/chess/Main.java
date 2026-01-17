package chess;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Board chessboard = new Board("rnbqkbnr/pppppppp/////PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        Scanner scanner = new Scanner(System.in);
        while (chessboard.isActive()) {
            Graphics.drawBoard(chessboard);
            System.out.print("Enter a move: ");
            Move tentativeMove = new Move(scanner.nextLine());
            // Board.move(tentativeMove);
        }
        scanner.close();

        // ArrayList<Piece> bishops = chessboard.getFilteredPieces(Piece.Colour.BLACK, Piece.Type.BISHOP);
        // Piece bishop = bishops.get(0);

        // ArrayList<Piece> blockingPieces = bishop.obstructingPieces(chessboard, chessboard.getSquareAt(2, 7));

        // for (int i = 0; i < blockingPieces.size(); i++) {
        //     Square temp = chessboard.getPieceSquare(blockingPieces.get(i));
        //     System.out.println(temp.getRank() + " " + temp.getFile());
        // }
    }
}
