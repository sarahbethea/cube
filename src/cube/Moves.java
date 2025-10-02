package cube;

public class Moves {
    public static final int T=0, F=1, D=2, B=3, L=4, R=5;

    private Moves() {} // prevent instantiation

    public static void rotateFaceCW(String[][][] cube, int f) {
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

    public static void rotateFaceCCW(String[][][] cube, int f) {
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

    public static void rotateCubeCW(String[][][] cube) {
        for (int i = 0; i < 6; i++) {
            rotateFaceCW(cube, i);
        }

        String[][] temp = cube[T];
        cube[T] = cube[L];
        cube[L] = cube[D];
        cube[D] = cube[R];
        cube[R] = temp;
    }

    public static void rotateCubeForward(String[][][] cube) {
        String[][] temp = cube[T];
        cube[T] = cube[F];
        cube[F] = cube[D];
        cube[D] = cube[B];
        cube[B] = temp;

        rotateFaceCW(cube, R);
        rotateFaceCCW(cube, L);
    }

    public static void rotateTopRowCW(String[][][] cube) {
        rotateFaceCW(cube, 0);

        String[] temp = cube[R][0].clone();
        cube[R][0] = cube[B][0].clone();
        cube[B][0] = cube[L][0].clone();
        cube[L][0] = cube[F][0].clone();
        cube[F][0] = temp;
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

    public static void moveU(String[][][] cube) {
        rotateTopRowCW(cube);
    }

    public static void moveD(String[][][] cube) {
        rotateCubeCW(cube);
        rotateCubeCW(cube);
        rotateTopRowCW(cube);
        rotateCubeCW(cube);
        rotateCubeCW(cube);
    }

    public static void moveR(String[][][] cube) {
        rotateCubeCW(cube);
        rotateCubeCW(cube);
        rotateCubeCW(cube);
        rotateTopRowCW(cube);
        rotateCubeCW(cube);
    }

    public static void moveL(String[][][] cube) {
        rotateCubeCW(cube);
        rotateTopRowCW(cube);
        rotateCubeCW(cube);
        rotateCubeCW(cube);
        rotateCubeCW(cube);
    }

    static void applyMove(String[][][] cube, String move) {
        switch(move) {
            case "U":
                moveU(cube);
                break;
            case "D":
                moveD(cube);
                break;
            case "R":
                moveR(cube);
                break;
            case "L":
                moveL(cube);
                break;
            case "F":
                moveF(cube);
                break;
            case "B":
                moveB(cube);
                break;
            case "U'":
                moveU(cube);
                moveU(cube);
                moveU(cube);
                break;
            case "D'":
                moveD(cube);
                moveD(cube);
                moveD(cube);
                break;
            case "R'":
                moveR(cube);
                moveR(cube);
                moveR(cube);
                break;
            case "L'":
                moveL(cube);
                moveL(cube);
                moveL(cube);
                break;
            case "F'":
                moveF(cube);
                moveF(cube);
                moveF(cube);
                break;
            case "B'":
                moveB(cube);
                moveB(cube);
                moveB(cube);
                break;
            default:
                throw new IllegalArgumentException("Invalid move: " + move);
        }

    }
    
}
