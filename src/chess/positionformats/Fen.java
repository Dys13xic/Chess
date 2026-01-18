package chess.positionformats;

import chess.Move;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// TODO This class is very rough, clean it up!
public class Fen implements PositionFormats{
    String fen;

    public Fen(String notation) {
        fen = notation;
    }

    public void loadPosition(chess.Board chessBoard) {
        // Validate FEN structure
        if (!fen.matches("^([rnbqkpRNBQKP1-8]{1,8}\\/){7}[rnbqkpRNBQKP1-8]{1,8}\\s[w,b]\\s([kqKQ]{1,4}|-)\\s(([a-h][3,6])|-)\\s\\d{1,3}\\s\\d{1,3}$")) {
            // throw new IllegalArgumentException();
        }

        String[] fields = fen.split(" ");

        // 1 - Position
        String[] position = fields[0].split("/");
        Collections.reverse(Arrays.asList(position));           // TODO should I replace this with more handwritten code?

        for (int rank = 0; rank < position.length; rank++) {
            int rankSquareCount = 0;
            for (int file = 0; file < position[rank].length(); file++) {
                Piece currentPiece;
                Piece.Colour colour = Piece.Colour.WHITE;
                switch(position[rank].charAt(file)) {
                    case 'p':
                        colour = Piece.Colour.BLACK;
                    case 'P':
                        currentPiece = new Pawn(colour, false); // TODO actually determine enpassant
                        chessBoard.insertPiece(rank, file, currentPiece);
                        rankSquareCount++;
                        break;

                    case 'r':
                        colour = Piece.Colour.BLACK;
                    case 'R':
                        currentPiece = new Rook(colour);
                        chessBoard.insertPiece(rank, file, currentPiece);
                        rankSquareCount++;
                        break;

                    case 'n':
                        colour = Piece.Colour.BLACK;
                    case 'N':
                        currentPiece = new Knight(colour);
                        chessBoard.insertPiece(rank, file, currentPiece);
                        rankSquareCount++;
                        break;

                    case 'b':
                        colour = Piece.Colour.BLACK;
                    case 'B':
                        currentPiece = new Bishop(colour);
                        chessBoard.insertPiece(rank, file, currentPiece);
                        rankSquareCount++;
                        break;

                    case 'q':
                        colour = Piece.Colour.BLACK;
                    case 'Q':
                        currentPiece = new Queen(colour);
                        chessBoard.insertPiece(rank, file, currentPiece);
                        rankSquareCount++;
                        break;

                    case 'k':
                        colour = Piece.Colour.BLACK;
                    case 'K':
                        currentPiece = new King(colour, false); // TODO determine if in check
                        chessBoard.insertPiece(rank, file, currentPiece);
                        rankSquareCount++;
                        break;

                    default:
                        char character = position[rank].charAt(file);
                        if (character < '0' || character > '9') {
                            // TODO throw error
                        }
                        int emptySquares = character - '0';
                        
                        if (file + emptySquares < chessBoard.getFileCount()) {
                            for (int i = 0; i < emptySquares; i++) {
                                chessBoard.insertPiece(rank, file + i, null);
                                rankSquareCount++;
                            }
                        }
                        else {
                            // Throw exception
                        }

                        break;
                }
            }
            if (rankSquareCount != chessBoard.getRankCount()) {
                throw new IllegalArgumentException();
            }
        }

        // TODO 3 - Castle Rights
        // If king has moved or rook has moved Castle Rights must accurately reflect that.

        // Ensure there is only one king for each side

        // TODO 4 - En Passant
    }

    public ArrayList<Move> getMoves() {
        String[] fields = fen.split(" ");
        int moveCount = Integer.parseInt(fields[5]);
        ArrayList<Move> moveList = new ArrayList<Move>();
        for (int i = 0; i < moveCount; i++) {
            moveList.add(null);
        }
        return moveList;
    }
    public Piece.Colour getActivePlayer() {
        String[] fields = fen.split(" ");
        return fields[1].equals("w") ? Piece.Colour.WHITE : Piece.Colour.BLACK;
    }
    public int getHalfMoveClock() {
        String[] fields = fen.split(" ");
        return Integer.parseInt(fields[4]);
    }

    public String export(chess.Board chessBoard) {
        return "";
    }
}