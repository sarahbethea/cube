package cube;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;
import java.util.List;

import cube.Moves;
import cube.PrintCube;
import cube.Randomizer;
import cube.Simplifier;
import cube.Solve;

public class App {
    static Stack<String> moves = new Stack<>();

    private static final String[][][] SOLVED_TEMPLATE = {
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

    // Helper to create copy of cube for each game
    private static String[][][] deepCopy(String[][][] src) {
        String[][][] out = new String[src.length][][];
        for (int i = 0; i < src.length; i++) {
            out[i] = new String[src[i].length][];
            for (int j = 0; j < src[i].length; j++) {
                out[i][j] = Arrays.copyOf(src[i][j], src[i][j].length);
            }
        }
        return out;
    }

    public static void main(String[] args) throws Exception {
        String[][][] SOLVED = deepCopy(SOLVED_TEMPLATE);

        PrintCube.printCubeBlocks(SOLVED);

        Scanner scn = new Scanner(System.in);

        int argsIndex = 0;

        List<String> solution = new ArrayList<>();

        while (true) {
            String move = "";
            if (args.length > argsIndex) {
                move = args[argsIndex];
                argsIndex++;
            } else {
                move = scn.nextLine();
            }

            switch(move.toUpperCase()){
                case "U":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "U");
                    break;
                case "D":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "D");
                    break;
                case "R":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "R");
                    break;
                case "L":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "L");
                    break;
                case "F":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "F");
                    break;
                case "B":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "B");
                    break;
                case "U'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "U'");
                    break;
                case "D'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "D'");
                    break;
                case "R'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "R'");
                    break;
                case "L'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "L'");
                    break;
                case "F'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "F'");
                    break;
                case "B'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "B'");
                    break;
                case "SOLVE":
                    List<String> sln = Solve.solve(moves, solution);
                    Moves.applyMoves(SOLVED, sln);
                    PrintCube.printCubeBlocks(SOLVED);
                    break;
                case "RANDOMIZE":
                    System.out.println("Enter number of moves");
                    int numMoves = Integer.parseInt(scn.nextLine());
                    Randomizer.randomize(SOLVED, numMoves);
                    break;
                case "SIMPLIFY":
                    String[] movesArr = moves.toArray(new String[0]);
                    String[] results = Simplifier.simplify(movesArr);
                    System.out.println("Simplified moves:");
                    for (String m : results) {
                        System.out.print(m + " ");
                    }
                    System.out.println();

                    System.out.println("Do you want to solve using simplified moves? (y for yes)");
                    String shouldSolve = scn.nextLine();
                    System.out.println(shouldSolve);
                    if (shouldSolve.toUpperCase().equals("Y")) {
                        Stack<String> resultsStack = new Stack<>();
                        for (String result : results) {
                            resultsStack.push(result);
                        }
                        sln = Solve.solve(resultsStack, solution);
                        Moves.applyMoves(SOLVED, sln);
                        PrintCube.printCubeBlocks(SOLVED);
                    } 
                    break;
                case "E":
                    PrintCube.printCubeBlocks(SOLVED);
                    break;
                case "Q":
                    scn.close();
                    return;
            }
        }

    }
}
