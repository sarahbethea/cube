package cube;

/**
 * ANSI-based printers for a 6×3×3 cube.
 *
 * <p>Outputs either:</p>
 * <ul>
 *   <li>raw tokens per face ({@link #printCube})</li>
 *   <li>colored 3×3 blocks per face using 24-bit ANSI ({@link #printCubeBlocks})</li>
 * </ul>
 *
 * <p>Notes:</p>
 * <ul>
 *   <li>Color is derived from the last char of each token (e.g., {@code "w","b","r"...}).</li>
 *   <li>If a token starts with {@code '1'} and {@link #SHOW_MARKER_DOT} is true, a dot is drawn.</li>
 *   <li>Faces are printed sequentially (not an unfolded net).</li>
 *   <li>Requires a terminal that supports 24-bit ANSI colors.</li>
 * </ul>
 */
public class PrintCube {
    static final String RESET = "\u001B[0m";
    static final boolean SHOW_MARKER_DOT = true;

    /**
     * Print literal tokens (no colors), 3×3 per face, faces separated by a blank line.
     * @param cube 6×3×3 cube state
     */
    public static void printCube(String[][][] cube) {
        for (int f = 0; f < 6; f++) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    System.out.print(cube[f][r][c]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static String bg(int r, int g, int b) {
        return "\u001B[48;2;" + r + ";" + g + ";" + b + "m";
    }

    private static String faceBG(char ch) {
        switch (ch) {
            case 'w': return bg(255,255,255);   //white
            case 'y': return bg(255,205,0);     //yellow
            case 'b': return bg(0,102,204);     //blue
            case 'g': return bg(0,150,0);       //green
            case 'r': return bg(220,20,60);     //red
            case 'o': return bg(255,140,0);     //orange
            default:  return bg(50,50,50);      //gray for unknown
        }
    }

    private static String tile(String token) {
        char colorChar = token.charAt(token.length() - 1);
        String bg = faceBG(colorChar);
        boolean isMarker = SHOW_MARKER_DOT && token.length() > 1 && token.charAt(0) == '1';

        if (isMarker) {
            String fg = "\u001B[38;2;20;20;20m";
            return bg + " " + fg + "·" + RESET;
        } else {
            return bg + "  " + RESET;
        }
    }

    /**
     * Pretty-print each face as a 3×3 colored block (faces printed sequentially).
     * @param cube 6×3×3 cube state
     */
    public static void printCubeBlocks(String[][][] cube) {
        final String VERT_GAP = "  ";
        final int ROW_WIDTH = 2*3 + 2;
        final String ROW_SEP = " ".repeat(ROW_WIDTH);

        for (int f = 0; f < 6; f++) {
            System.out.println(ROW_SEP);

            for (int r = 0; r < 3; r++) {
                StringBuilder row = new StringBuilder();
                for (int c = 0; c < 3; c++) {
                    row.append(tile(cube[f][r][c]));
                    if (c < 2) row.append(VERT_GAP);
                }
                System.out.println(row.toString() + RESET);
                if (r < 2) System.out.println(ROW_SEP);
            }
            System.out.println(ROW_SEP);
        }
    }
}
