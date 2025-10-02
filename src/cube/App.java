package cube;
import cube.Moves;
import cube.PrintCube;
import cube.Randomizer;
import cube.Simplifier;

public class App {
    public static void main(String[] args) throws Exception {
        String[][][] SOLVED = {
            {
                {"1w", "w", "w"}, 
                {"w", "w", "w"},
                {"w", "w", "w"}
            },

            {
                {"1b", "b", "b"},
                {"b", "b", "b"},
                {"b", "b", "b"} 
            },

            {
                {"1y", "y", "y"}, 
                {"y", "y", "y"}, 
                {"y", "y", "y"} 
            },

            {
                {"1g", "g", "g"}, 
                {"g", "g", "g"},
                {"g", "g", "g"} 
            }, 

            {
                {"1r", "r", "r"}, 
                {"r", "r", "r"}, 
                {"r", "r", "r"} 
            },

            {
                {"1o", "o", "o"}, 
                {"o", "o", "o"}, 
                {"o", "o", "o"} 
            }
        };

        for (String arg : args) {
            Moves.applyMove(SOLVED, arg.toUpperCase());
        }

        // Randomizer.randomize(SOLVED, 12);

        PrintCube.printCubeBlocks(SOLVED);

        if (args.length == 0) {
            System.out.println("Usage: java App.java <moves>");
            return;
        }

        System.out.println("Simplified moves:");
        String[] simplifiedMoves = Simplifier.simplify(args);

        for (String move : simplifiedMoves) {
            System.out.println(move);
        }

    }
}
