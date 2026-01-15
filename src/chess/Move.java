package chess;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import chess.pieces.Piece;

public class Move {
    
    public enum Type {
        STANDARD,
        CASTLE,
        OFFER_DRAW,
        RESULT
    }

    public enum Check {
        STANDARD,
        MATE
    }

    private static final char START_RANK = '1';
    private static final char START_FILE = 'a';
    private static final char END_RANK = '8';
    private static final char END_FILE = 'h';

    private static final Pattern MOVE_PATTERN = Pattern.compile("^(((?<piece>[NBRQK])?(?<rank>[a-h])?(?<file>[1-8])?(?<capture>x)?(?<rankAndFile>[a-h][1-8])(?<promote>=[NBRQ])?)|(?<castle>O-O(-O)?))(?<check>[+#])?$");
    private final String PIECE_LIST_STRING = "NBRQK";

    // int move;
    Type type;
    int sourceRank = -1;
    int sourceFile = -1;
    int targetRank = -1;
    int targetFile = -1;
    Piece.Type piece;
    Piece.Type promotedTo;
    boolean capture;
    Check check;
    String notation;

    private Piece.Type charToPieceType(char notation) {
        switch (notation) {
            case 'N':
                return Piece.Type.KNIGHT;
            case 'B':
                return Piece.Type.BISHOP;
            case 'R':
                return Piece.Type.ROOK;
            case 'Q':
                return Piece.Type.QUEEN;
            case 'K':
                return Piece.Type.KING;
            default:
                return null;
        }
    }

    private static int fileCharToInt(char file) {
        return file - START_FILE;
    }

    private static int rankCharToInt(char rank) {
        return rank - START_RANK;
    }

    public Move(String notation) {
        //  TODO Validate the notation with regex.

        // Check
        if (notation.endsWith("+")) {
            check = Check.STANDARD;
        }
        else if (notation.endsWith("#")) {
            check = Check.MATE;
        }

        // Draw
        if (notation.startsWith("=")) {
            type = Type.OFFER_DRAW;
        }

        // Castle
        else if (notation.startsWith("O-O")) {
            type = Type.CASTLE;
            piece = Piece.Type.ROOK;

            if (notation.startsWith("O-O-O")) {
                sourceFile = fileCharToInt('h');
            }
            else {
                sourceFile = fileCharToInt('a');
            }
        }

        // Standard Move
        else {
            char current = notation.charAt(notation.length() - 1);

            for (int i = notation.length() - 1; i >= 0; i--) {
                current = notation.charAt(i);

                // Rank
                if ('a' <= current && current <= 'h') {
                    if (targetRank == -1) targetRank = rankCharToInt(current);
                    else sourceRank = rankCharToInt(current);
                }
                // File
                else if ('1' <= current && current <= '8') {
                    if (targetFile == -1) targetFile = fileCharToInt(current);
                    else sourceFile = fileCharToInt(current);
                }
                // Piece
                else if (PIECE_LIST_STRING.contains(Character.toString(current))) {
                    if (i > 0 && notation.charAt(i - 1) == '=') {
                        promotedTo = charToPieceType(current);
                    }
                    else {
                        piece = charToPieceType(current);
                    }
                }
            }
        }
    }
}
