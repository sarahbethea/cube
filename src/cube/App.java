package cube;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;
import java.util.List;

import cube.Moves;
import cube.PrintCube;
import cube.Randomizer;
import cube.Simplifier;

public class App {

    static Stack<String> moves = new Stack<>();

    // static void solve(Stack<String> moves) {
    //     while (!moves.empty()) {
    //         String move = moves.pop();
    //         String reverse = (move.contains("'")) ? move.substring(0, 1) : move + "'";
    //         System.out.print(reverse + " ");
    //     }
    // }

    static List<String> solve(Stack<String> moves, List<String> solution) {
        while (!moves.empty()) {
            String move = moves.pop();
            String reverse = (move.contains("'")) ? move.substring(0, 1) : move + "'";
            System.out.print(reverse + " ");
            solution.add(reverse);
        }
        return solution;
    }

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
                    List<String> sln = solve(moves, solution);
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

                    System.out.println("Do you want to solve using simplified moves? y for yes");
                    String shouldSolve = scn.nextLine();
                    System.out.println(shouldSolve);
                    if (shouldSolve.toUpperCase().equals("Y")) {
                        Stack<String> resultsStack = new Stack<>();
                        for (String result : results) {
                            resultsStack.push(result);
                        }
                        sln = solve(resultsStack, solution);
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
