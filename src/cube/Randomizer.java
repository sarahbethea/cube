package cube;

import java.util.Random;
import cube.Moves;

public class Randomizer {
    private static final String[] MOVES = {"U", "D", "L", "R", "F", "B", "U'", "D'", "L'", "R'", "F'", "B'"};

    public static void randomize(String[][][] cube, int numMoves) {
        Random random = new Random();

        for (int i = 0; i < numMoves; i++) {
            int randomIndex = random.nextInt(MOVES.length - 1);
            Moves.applyMove(cube, MOVES[randomIndex]);
        }
    }
}



