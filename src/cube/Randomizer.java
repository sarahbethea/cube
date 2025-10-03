package cube;

import java.util.Random;
import java.util.Stack;

import cube.Moves;

public class Randomizer {
    private static final String[] MOVES = {"U", "D", "L", "R", "F", "B", "U'", "D'", "L'", "R'", "F'", "B'"};

    public static void randomize(String[][][] cube, int numMoves) {
        Random random = new Random();

        Stack<String> moves = new Stack<>();

        for (int i = 0; i < numMoves; i++) {
            System.out.print("random move: " + i);
            int randomIndex = random.nextInt(MOVES.length - 1);
            String move = MOVES[randomIndex];
            moves.push(move);
            System.out.println(moves);
            Moves.applyMove(cube, move);
        }
    }
}



