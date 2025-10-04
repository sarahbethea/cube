package cube;

import java.util.Random;
import java.util.Stack;

import cube.Moves;
import cube.PrintCube;

public class Randomizer {
    private static final String[] MOVES = {"U", "D", "L", "R", "F", "B", "U'", "D'", "L'", "R'", "F'", "B'"};

    public static void randomize(String[][][] cube, int numMoves, Stack<String>moves) {
        Random random = new Random();

        for (int i = 0; i < numMoves; i++) {
            int randomIndex = random.nextInt(MOVES.length - 1);
            String move = MOVES[randomIndex];
            moves.push(move);
            Moves.applyMove(cube, move);
        }
        System.out.println(moves);

        PrintCube.printCubeBlocks(cube);
    }
}



