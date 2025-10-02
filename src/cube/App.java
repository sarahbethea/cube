package cube;
import cube.Moves;
import cube.PrintCube;

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

        // Fancy color output without titles:
        PrintCube.printCubeBlocks(SOLVED);
        System.out.println("----------");

        Moves.applyMove(SOLVED, "F'");
        Moves.applyMove(SOLVED, "U");
        Moves.applyMove(SOLVED, "D");
        // Moves.applyMove(SOLVED, "U");

        System.out.println("----------");
        PrintCube.printCubeBlocks(SOLVED);

    }
}
