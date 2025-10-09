package cube;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Computes an inverse move sequence to undo a move history.
 *
 * <p>Behavior:</p>
 * <ul>
 *   <li>Consumes the given LIFO history (empties the stack).</li>
 *   <li>Returns the inverse sequence in the order it should be applied to restore state.</li>
 *   <li>Understands quarter turns (e.g., U, R'), and double turns (e.g., U2). For doubles, inverse is itself.</li>
 * </ul>
 */
public class Solve {
    // Inverse: X -> X', X' -> X, X2 / X2' -> X2
    private static String invert(String move) {
        if (move == null || move.isEmpty()) throw new IllegalArgumentException("Empty move");
        char face = move.charAt(0);
        if (move.indexOf('2') >= 0) return face + "2";
        return move.endsWith("'") ? String.valueOf(face) : (face + "'");
    }

    /**
     * Pop all moves from {@code moves} and build the inverse sequence.
     *
     * @param moves LIFO history (mutated: emptied by this method)
     * @return list of inverse tokens to apply in order
     */
    public static List<String> solve(Stack<String> moves) {
        List<String> solution = new ArrayList<String>();

        while (!moves.empty()) {
            String move = moves.pop();
            String inverted = invert(move);
            System.out.print(inverted + " ");
            solution.add(inverted);
        }
        return solution;
    }

    
}
