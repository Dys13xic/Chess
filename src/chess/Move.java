package chess;

import java.util.regex.Pattern;

import chess.pieces.Piece;

public class Move {
    
    public enum Check {
        STANDARD,
        MATE
    }

    private static final Pattern NOTATION_PATTERN = Pattern.compile("^([NBRQK])?([a-h])?([1-8])?(x)?([a-h][1-8])(=[NBRQ])?(\\+|#)?$|^O-O(-O)?$");
    private static final String PIECE_SYMBOLS = "NBRQK";
    private static final char START_RANK = '1';
    private static final char START_FILE = 'a';
    private static final char END_RANK = '8';
    private static final char END_FILE = 'h';
    private static final String KINGSIDE_CASTLING = "O-O";
    private static final String QUEENSIDE_CASTLING = "O-O-O";

    int sourceRank = -1;
    int sourceFile = -1;
    int targetRank = -1;
    int targetFile = -1;
    Piece.Type piece = null;
    Piece.Type promotedTo = null;
    boolean capture = false;
    Check check = null;
    boolean drawOffer = false;

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

    private static int fileToIndex(char file) {
        return file - START_FILE;
    }

    private static int rankToIndex(char rank) {
        return rank - START_RANK;
    }

    public Move(String notation) {
        //  Validate notation with regex.
        if (NOTATION_PATTERN.matcher(notation).find() == false) {
            throw new IllegalArgumentException();
        }

        // Check/Mate
        if (notation.endsWith("+")) {
            check = Check.STANDARD;
        }
        else if (notation.endsWith("#")) {
            check = Check.MATE;
        }

        // Draw Offer
        if (notation.endsWith("(=)")) {
            drawOffer = true;
        }

        // Castle
        else if (notation.startsWith(KINGSIDE_CASTLING)) {
            piece = Piece.Type.ROOK;

            if (notation.startsWith(QUEENSIDE_CASTLING)) {
                sourceFile = fileToIndex('h');
                targetFile = fileToIndex('d');
            }
            else {
                sourceFile = fileToIndex('a');
                targetFile = fileToIndex('f');
            }
        }

        // Standard Move
        else {
            char current = notation.charAt(notation.length() - 1);

            for (int i = notation.length() - 1; i >= 0; i--) {
                current = notation.charAt(i);

                // Rank
                if (START_RANK <= current && current <= END_RANK) {
                    if (targetRank == -1) targetRank = rankToIndex(current);
                    else sourceRank = rankToIndex(current);
                }
                // File
                else if (START_FILE <= current && current <= END_FILE) {
                    if (targetFile == -1) targetFile = fileToIndex(current);
                    else sourceFile = fileToIndex(current);
                }
                // Piece
                else if (PIECE_SYMBOLS.contains(Character.toString(current))) {
                    if (i == 0) {
                        piece = charToPieceType(current);
                    }
                    else {
                        promotedTo = charToPieceType(current);
                    }
                }
                else if (current == 'x') {
                    capture = true;
                }
            }

            if (piece == null) {
                piece = Piece.Type.PAWN;
            }
        }
    }
}