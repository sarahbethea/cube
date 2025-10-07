package cube;

import java.util.List;

public class Moves {
    public static final int T=0, F=1, D=2, B=3, L=4, R=5;

    private Moves() {} // prevent instantiation

    private static void rotateCubeBackward(String[][][] cube) {
        String[][] temp = cube[T];
        cube[T] = cube[B];
        cube[B] = cube[D];
        cube[D] = cube[F];
        cube[F] = temp;

        rotateFaceCCW(cube, R);
        rotateFaceCW(cube, T);
        rotateFaceCW(cube, T);
        rotateFaceCW(cube, B);
        rotateFaceCW(cube, B);
        rotateFaceCW(cube, L);
    }

    private static void rotateCubeForward(String[][][] cube) {
        String[][] temp = cube[T];
        cube[T] = cube[F];
        cube[F] = cube[D];
        cube[D] = cube[B];
        cube[B] = temp;

        rotateFaceCW(cube, R);
        rotateFaceCW(cube, D);
        rotateFaceCW(cube, D);
        rotateFaceCW(cube, B);
        rotateFaceCW(cube, B);
        rotateFaceCCW(cube, L);
    }

    private static void moveF(String[][][] cube) {
        rotateCubeForward(cube);
        rotateTopRowCW(cube);
        rotateCubeBackward(cube);
    }

    private static void moveB(String[][][] cube) {
        rotateCubeBackward(cube);
        rotateTopRowCW(cube);
        rotateCubeForward(cube);
    }


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
    
        // Counterclockwise: rotated[row][col] = orig[col][2 - row]
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

    public static void rotateTopRowCW(String[][][] cube) {
        rotateFaceCW(cube, 0);

        String[] temp = cube[R][0].clone();
        cube[R][0] = cube[B][0].clone();
        cube[B][0] = cube[L][0].clone();
        cube[L][0] = cube[F][0].clone();
        cube[F][0] = temp;
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

    static void applyMoves(String[][][] cube, List<String> moves) {
        for (int i = 0; i < moves.size(); i++) {
            String move = moves.get(i);
            applyMove(cube, move);
        }
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
            case "U2":
                moveU(cube);
                moveU(cube);
                break;
            case "D2":
                moveD(cube);
                moveD(cube);
                break;
            case "R2":
                moveR(cube);
                moveR(cube);
                break;
            case "L2":
                moveL(cube);
                moveL(cube);
                break;
            case "F2":
                moveF(cube);
                moveF(cube);
                break;
            case "B2":
                moveB(cube);
                moveB(cube);
                break;
            case "U2'":
                moveU(cube);
                moveU(cube);
                moveU(cube);
                moveU(cube);
                moveU(cube);
                moveU(cube);
                break;
            case "D2'":
                moveD(cube);
                moveD(cube);
                moveD(cube);
                moveD(cube);
                moveD(cube);
                moveD(cube);
                break;
            case "R2'":
                moveR(cube);
                moveR(cube);
                moveR(cube);
                moveR(cube);
                moveR(cube);
                moveR(cube);
                break;
            case "L2'":
                moveL(cube);
                moveL(cube);
                moveL(cube);
                moveL(cube);
                moveL(cube);
                moveL(cube);
                break;
            case "F2'":
                moveF(cube);
                moveF(cube);
                moveF(cube);
                moveF(cube);
                moveF(cube);
                moveF(cube);
                break;
            case "B2'":
                moveB(cube);
                moveB(cube);
                moveB(cube);
                moveB(cube);
                moveB(cube);
                moveB(cube);
                break;
            default:
                throw new IllegalArgumentException("Invalid move: " + move);
        }

    }
    
}
