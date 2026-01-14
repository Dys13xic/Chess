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

    private static final Pattern MOVE_PATTERN = Pattern.compile("^(((?<piece>[NBRQK])?(?<rank>[a-h])?(?<file>[1-8])?(?<capture>x)?(?<rankAndFile>[a-h][1-8])(?<promote>=[NBRQ])?)|(?<castle>O-O(-O)?))(?<check>[+#])?$");

    int move;
    Type type;
    int sourceRank;
    int sourceFile;
    int targetRank;
    int targetFile;
    Piece.Type selected;
    Piece.Type promotedTo;
    boolean capture;
    boolean check;
    boolean mate;
    String notation;

    public Move(String notation) {
        if (notation.equals("=")) {
            type = Type.OFFER_DRAW; 
        }
        else {
            Matcher matcher = MOVE_PATTERN.matcher(notation);

            String castle = matcher.group("castle");
            String checkType = matcher.group("check");


            final int hFile = 7;
            final int aFile = 0;
            if (castle.startsWith("O-O-O")) {
                sourceFile = hFile;
            }
            else if (castle.startsWith("O-O")) {
                sourceFile = aFile;
            }
        }
    }
}
