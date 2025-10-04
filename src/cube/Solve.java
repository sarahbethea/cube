package cube;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solve {

    public static List<String> solve(Stack<String> moves) {
        List<String> solution = new ArrayList<String>();

        while (!moves.empty()) {
            String move = moves.pop();
            String reverse = (move.contains("'")) ? move.substring(0, 1) : move + "'";
            System.out.print(reverse + " ");
            solution.add(reverse);
        }
        moves.clear();
        return solution;
    }

    
}
