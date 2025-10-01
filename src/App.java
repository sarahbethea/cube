public class App {
    // top is index 0
    // right is 5
    // left is 4
    // bottom is 2
    // front is 1
    //  back is 3

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

    static void printCube2D(String[][] cube) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(cube[i][j]);
                if ((j + 1) % 3 == 0) System.out.println(); // newline after 3rd, 6th, 9th
                else System.out.print(" ");  
            }
            System.out.println();
        }
    }

    // cube[6][3][3] is the shape
    static String[][] rotateFace2D(String[][] f, int i) {
        String temp = f[i][0];

        f[i][0] = f[i][6];
        f[i][6] = f[i][8];
        f[i][8] = f[i][2];
        f[i][2] = temp;

        temp = f[i][1];
        f[i][1] = f[i][3];
        f[i][3] = f[i][7];
        f[i][7] = f[i][5];
        f[i][5] = temp;

        return f;
        
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

        // FRONT = SOLVED[0][1]

        String[][] SOLVED2D = {
            {"0w", "1w", "2w",
            "3w", "Cw", "4w",
            "5w", "6w", "7w"},

            {"0b", "1b", "2b",
            "3b", "Cb", "4b",
            "5b", "6b", "7b"},

            {"0y", "1y", "2y", 
            "3y", "Cy", "4y", 
            "5y", "6y", "7y"},

            {"0g", "1g", "2g", 
            "3g", "Cg", "4g", 
            "5g", "6g", "7g"},

            {"0r", "1r", "2r", 
            "3r", "Cr", "4r", 
            "5r", "6r", "7r"},

            {"0o", "1o", "2o", 
            "3o", "Co", "4o", 
            "5o", "6o", "7o"}
        };

        // Whole cube jagged array to play with
        String[][] JAGGED_SOLVED = {
            {"w", "w", "w", "o", "o", "o", "r", "r", "r"},
            {"w", "w", "w", "o", "o", "o", "r", "r", "r"},
            {"w", "w", "w", "o", "o", "o", "r", "r", "r"},
            {"b", "b", "b"},
            {"b", "b", "b"},
            {"b", "b", "b"},
            {"g", "g", "g"},
            {"g", "g", "g"},
            {"g", "g", "g"},
            {"y", "y", "y"},
            {"y", "y", "y"},
            {"y", "y", "y"},
        };

        printCube(SOLVED);

        System.out.println("----------");


        // if move == U: rotateTopFaceCW
        // if move == D: 2 90 degree CW rotations and flip
        // if move == R: 3 rotations CW rotations and flip
        // if move == L: 1 rotation CW and flip
        // if move == F: 


        // U move
        // rotateTopRowCW(SOLVED);
        // printCube(SOLVED);

        // // D move
        // rotateCubeCW(SOLVED);
        // rotateCubeCW(SOLVED);
        // rotateTopRowCW(SOLVED);
        // rotateCubeCW(SOLVED);
        // rotateCubeCW(SOLVED);
        // printCube(SOLVED);

        // R move
        // System.out.println("STEP 1: ");
        // rotateCubeCW(SOLVED);
        // printCube(SOLVED);

        // System.out.println();
        // System.out.println("STEP 2: ");

        // rotateCubeCW(SOLVED);
        // printCube(SOLVED);

        // System.out.println();
        // System.out.println("STEP 3: ");


        // rotateCubeCW(SOLVED);
        // printCube(SOLVED);
        
        // System.out.println();
        // System.out.println("STEP 4: ");

        // rotateTopRowCW(SOLVED);
        // printCube(SOLVED);

        
        // System.out.println();
        // System.out.println("STEP 5: ");

        // rotateCubeCW(SOLVED);
        // printCube(SOLVED);

        //L Move
        // rotateCubeCW(SOLVED);
        // rotateTopRowCW(SOLVED);
        // rotateCubeCW(SOLVED);
        // rotateCubeCW(SOLVED);
        // rotateCubeCW(SOLVED);

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
