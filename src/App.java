// T (top) index: 0
// R (right) index: 5
// L (left) index: 4
// D (bottom) index: 2
// F (front) index: 1
// B (back) index: 3

// if move == U: rotateTopFaceCW
// if move == D: 2 90 degree CW rotations and flip
// if move == R: 3 rotations CW rotations and flip
// if move == L: 1 rotation CW and flip
// if move == F: (direct)

public class App {
    static void printCube(String[][][] cube) {
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

    static final String RESET = "\u001B[0m";

    static String bg(int r, int g, int b) {
        return "\u001B[48;2;" + r + ";" + g + ";" + b + "m";
    }

    static String faceBG(char ch) {
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

    static final boolean SHOW_MARKER_DOT = true;
    static String tile(String token) {
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

    static void printCubeBlocks(String[][][] cube) {
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

    static void rotateFaceCW(String[][][] cube, int f) {
        // Rotates face 90 degrees clockwise 
        String[][] orig = cube[f];
        String[][] rotated = new String[3][3];

        // rotated[row][col] = old[2-col][row]
        for (int row = 0; row <  3; row++) {
            for (int col = 0; col < 3; col++) {
                rotated[row][col] = orig[2-col][row];
            }
        }   

        // copy rotated face back onto original
        for (int r = 0; r < 3; r++) {
            System.arraycopy(rotated[r], 0, orig[r], 0, 3);
        }
    }

    static void rotateFaceCCW(String[][][] cube, int f) {
        // Rotate face 90 degrees counterclockwise 
        String[][] orig = cube[f];
        String[][] rotated = new String[3][3];
    
        // CCW: rotated[row][col] = orig[col][2 - row]
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                rotated[row][col] = orig[col][2 - row];
            }
        }
        // copy back
        for (int r = 0; r < 3; r++) {
            System.arraycopy(rotated[r], 0, orig[r], 0, 3);
        }

    }

    static void rotateCubeCW(String[][][] cube) {
        for (int i = 0; i < 6; i++) {
            rotateFaceCW(cube, i);
        }
        // T, L, R, D
        int T = 0, R = 5, L = 4, D = 2;
        String[][] temp = cube[T];
        cube[T] = cube[L];
        cube[L] = cube[D];
        cube[D] = cube[R];
        cube[R] = temp;
    }

    static void rotateCubeForward(String[][][] cube) {
        int T = 0, B = 3, D = 2, F = 1, L = 4, R = 5;
        String[][] temp = cube[T];
        cube[T] = cube[F];
        cube[F] = cube[D];
        cube[D] = cube[B];
        cube[B] = temp;

        rotateFaceCW(cube, T);
        rotateFaceCW(cube, B);
        rotateFaceCCW(cube, F);
        rotateFaceCCW(cube, D);
        rotateFaceCW(cube, L);
        rotateFaceCCW(cube, R);

    }

    //helpers for row/col on direct f and b moves.
    static String[] getRow(String[][] face, int r) {
        return new String[]{ face[r][0], face[r][1], face[r][2] };
    }
    static void setRow(String[][] face, int r, String[] v) {
        face[r][0] = v[0]; face[r][1] = v[1]; face[r][2] = v[2];
    }
    static String[] getCol(String[][] face, int c) {
        return new String[]{ face[0][c], face[1][c], face[2][c] };
    }
    static void setCol(String[][] face, int c, String[] v) {
        face[0][c] = v[0]; face[1][c] = v[1]; face[2][c] = v[2];
    }
    static String[] rev(String[] a) {
        return new String[]{ a[2], a[1], a[0] };
    }

    //direct f and b moves
    static void moveF(String[][][] cube) {
        final int T=0, F=1, D=2, B=3, L=4, R=5;

        rotateFaceCW(cube, F);

        String[] u = getRow(cube[T], 2);
        String[] r = getCol(cube[R], 0);
        String[] d = getRow(cube[D], 0);
        String[] l = getCol(cube[L], 2);

        //reverse where needed
        setCol(cube[R], 0, u);
        setRow(cube[D], 0, r);
        setCol(cube[L], 2, d);
        setRow(cube[T], 2, rev(l));
    }

    static void moveB(String[][][] cube) {
        final int T=0, F=1, D=2, B=3, L=4, R=5;

        rotateFaceCW(cube, B);

        String[] u = getRow(cube[T], 0);
        String[] l = getCol(cube[L], 0);
        String[] d = getRow(cube[D], 2);
        String[] r = getCol(cube[R], 2);

        //reverse where needed
        setCol(cube[L], 0, u);
        setRow(cube[D], 2, l);
        setCol(cube[R], 2, rev(d));
        setRow(cube[T], 0, rev(r));
    }

    public static void rotateTopRowCW(String[][][] cube) {
        // top face rotates clockwise 90
        // bottom stays same
        // top rows of F, R, B, and L faces are swapped

        //rotate top face
        rotateFaceCW(cube, 0);

        int F = 1, R = 5, B = 3, L = 4;

        String[] temp = cube[R][0].clone();
        cube[R][0] = cube[B][0].clone();
        cube[B][0] = cube[L][0].clone();
        cube[L][0] = cube[F][0].clone();
        cube[F][0] = temp;
    }

    static void applyMove(String[][][] cube, String move) {
        switch(move) {
            case "U":
                rotateTopRowCW(cube);
                break;
            case "D":
                rotateCubeCW(cube);
                rotateCubeCW(cube);
                rotateTopRowCW(cube);
                rotateCubeCW(cube);
                rotateCubeCW(cube);
                break;
            case "R":
                rotateCubeCW(cube);
                rotateCubeCW(cube);
                rotateCubeCW(cube);
                rotateTopRowCW(cube);
                rotateCubeCW(cube);
                break;
            case "L":
                rotateCubeCW(cube);
                rotateTopRowCW(cube);
                rotateCubeCW(cube);
                rotateCubeCW(cube);
                rotateCubeCW(cube);
                break;
            case "F":
                moveF(cube);
                break;
            case "B":
                moveB(cube);
                break;
        }

    }

    public static void main(String[] args) throws Exception {

        String[][][] SOLVED = {
            {
                {"1w", "w", "w"}, //0 
                {"w", "w", "w"},
                {"w", "w", "w"}
            },

            {
                {"1b", "b", "b"},
                {"b", "b", "b"},
                {"b", "b", "b"} //1
            },

            {
                {"1y", "y", "y"}, 
                {"y", "y", "y"}, 
                {"y", "y", "y"} //2
            },

            {
                {"1g", "g", "g"}, 
                {"g", "g", "g"},
                {"g", "g", "g"} //3
            }, 

            {
                {"1r", "r", "r"}, 
                {"r", "r", "r"}, 
                {"r", "r", "r"} //4
            },

            {
                {"1o", "o", "o"}, 
                {"o", "o", "o"}, 
                {"o", "o", "o"} //5
            }
        };

        // Fancy color output without titles:
        printCubeBlocks(SOLVED);
        System.out.println("----------");

        applyMove(SOLVED, "F");
        applyMove(SOLVED, "U");

        System.out.println("----------");
        printCubeBlocks(SOLVED);
    }
}
