package cube;

import java.util.Random;
import java.util.Stack;

import cube.Moves;
import cube.PrintCube;

/**
 * Generates random move sequences and applies them to a cube state.
 *
 * <p>Notes:</p>
 * <ul>
 *   <li>Mutates {@code cube} in-place and appends each move to {@code moves}.</li>
 *   <li>Draws from quarter turns (with primes): U/D/L/R/F/B and U'/D'/L'/R'/F'/B'.</li>
 *   <li>Prints the full move history and the cube via {@link PrintCube}.</li>
 * </ul>
 */
public class Randomizer {
    private static final String[] MOVES = {"U", "D", "L", "R", "F", "B", "U'", "D'", "L'", "R'", "F'", "B'"};

    /**
     * Apply {@code numMoves} random moves from {@link #MOVES}.
     *
     * @param cube  6×3×3 cube state (mutated in-place)
     * @param numMoves number of random moves to apply (caller should ensure {@code > 0})
     * @param moves history stack; each chosen move is pushed to this stack
     */
    public static void randomize(String[][][] cube, int numMoves, Stack<String>moves) {
        Random random = new Random();

        for (int i = 0; i < numMoves; i++) {
            int randomIndex = random.nextInt(MOVES.length);
            String move = MOVES[randomIndex];
            moves.push(move);
            Moves.applyMove(cube, move);
        }
        System.out.println(moves);

        PrintCube.printCubeBlocks(cube);
    }
}



