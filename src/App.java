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
// if move == F: 


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
        // T, L, R, B
        int T = 0, R = 5, L = 4, B = 2;
        String[][] temp = cube[T];
        cube[T] = cube[L];
        cube[L] = cube[B];
        cube[B] = cube[R];
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
                break;
            case "B":
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


        printCube(SOLVED);
        System.out.println("----------");

        // // U move
        // applyMove(SOLVED, "U");
        // printCube(SOLVED);

        // // D move
        // applyMove(SOLVED, "D");
        // printCube(SOLVED);

        // // R move 
        // applyMove(SOLVED, "R");
        // printCube(SOLVED);



        //F Move


        rotateCubeForward(SOLVED);
        rotateTopRowCW(SOLVED);
        rotateCubeForward(SOLVED);
        rotateCubeForward(SOLVED);
        rotateCubeForward(SOLVED);

        System.out.println("----------");
        printCube(SOLVED);







    }
}
