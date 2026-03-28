package chess.positionformats;

import chess.Coordinate;
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

public class Fen implements PositionFormats{
    String fen;
    Piece.Colour activePlayer;
    int halfMoveClock;
    int moveCount;

    public Fen(String notation) {
        // Validate FEN structure
        if (!notation.matches("^([rnbqkpRNBQKP1-8]{1,8}\\/){7}[rnbqkpRNBQKP1-8]{1,8}\\s[w,b]\\s([kqKQ]{1,4}|-)\\s(([a-h][3,6])|-)\\s\\d{1,3}\\s\\d{1,3}$")) {
            // TODO throw exception
        }
        fen = notation;

        String[] fields = fen.split(" ");
        activePlayer = fields[1].equals("w") ? Piece.Colour.WHITE : Piece.Colour.BLACK;
        halfMoveClock = Integer.parseInt(fields[4]);
        moveCount = Integer.parseInt(fields[5]);
    }

    public void loadPosition(chess.Board board) {
        String[] fields = fen.split(" ");
        board.clear();

        String[] position = fields[0].split("/");
        Collections.reverse(Arrays.asList(position));

        for (int rank = 0; rank < position.length; rank++) {
            int file = 0;
            for (int i = 0; i < position[rank].length(); i++) {
                char currentCharacter = position[rank].charAt(i);
                Piece.Colour colour = Character.isUpperCase(currentCharacter) ? Piece.Colour.WHITE : Piece.Colour.BLACK;
                Piece currentPiece;

                switch(currentCharacter) {
                    case 'p':
                    case 'P':
                        currentPiece = new Pawn(colour, false);
                        break;

                    case 'r':
                    case 'R':
                        currentPiece = new Rook(colour);
                        break;

                    case 'n':
                    case 'N':
                        currentPiece = new Knight(colour);
                        break;

                    case 'b':
                    case 'B':
                        currentPiece = new Bishop(colour);
                        break;

                    case 'q':
                    case 'Q':
                        currentPiece = new Queen(colour);
                        break;

                    case 'k':
                    case 'K':
                        currentPiece = new King(colour, false, false);
                        break;

                    default:
                        char character = position[rank].charAt(file);
                        int emptySquares = character - '0';
                        file += emptySquares;
                        continue;
                }
                board.insertPiece(rank, file, currentPiece);
                file++;
            }
        }
 
        // TODO complete castle rights!
        String castleRights = fields[2];
        for (int i = 0; i < castleRights.length(); i++) {
            // switch(castleRight.charAt(i)) {
            //     case: 'w'
            //         board.getP
                    
            // }
        }

        String enPassantTarget = fields[3];
        if (!enPassantTarget.equals("-")) {
            Coordinate enPassantSquare = new Coordinate(enPassantTarget);
            Piece.Colour boardHalf = board.getHalf(enPassantSquare);
            int directionOfTravel = boardHalf == Piece.Colour.WHITE ? 1 : -1;
            
            Coordinate targetSquare = new Coordinate(enPassantSquare.getRank() + directionOfTravel, enPassantSquare.getFile());
            Piece targetPiece = board.getPieceAt(targetSquare);
            if (Pawn.class.isInstance(targetPiece)) {
                Pawn targetPawn = Pawn.class.cast(targetPiece);
                targetPawn.setEnPassantTarget(true);
            }
        }
    }

    public Piece.Colour getActivePlayer() {
        return activePlayer;
    }

    public int getHalfMoveClock() {
        return halfMoveClock;
    }

    public ArrayList<Move> getMoves() {
        ArrayList<Move> moveList = new ArrayList<Move>();
        for (int i = 0; i < moveCount; i++) {
            moveList.add(null);
        }
        return moveList;
    }

    public String export(chess.Board board) {
        return "";
    }
}