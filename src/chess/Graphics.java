package chess;

public class Graphics {

    public static final String ANSI_RESET_COLOUR = "\u001B[0m";

    /**
     * Clears terminal screen and returns cursor to top-left using ANSI escape codes
     */
    public static void clearDrawing() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String mergeColours(String foreground, String background) {
        if (foreground == null || background == null) {  // TODO add regex check for formatting
            // TODO throw exception
        }
        // Remove ANSI escape code terminating character 'm'
        foreground = foreground.substring(0, foreground.length() -1 );
        // Extract colour defining portion of ANSI escape code, i.e. remove "\033["
        background = background.substring(2);

        return (foreground + ';' + background);
    }

}
