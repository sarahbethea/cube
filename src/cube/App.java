package cube;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

import cube.Moves;
import cube.PrintCube;
import cube.Randomizer;
import cube.Simplifier;
import cube.Solve;

public class App {
    private static Stack<String> moves = new Stack<>();

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

    // Print menu of options
    private static void printMenu() {
        System.out.println("=== CUBE COMMAND MENU ===");
        System.out.println("Moves: U, U', U2, D, D', D2, R, R', R2, L, L', L2, F, F', F2, B, B', B2");
        System.out.println();
        System.out.println("E          Enter a sequence of moves and display cube state");
        System.out.println("Q          Quit");
        System.out.println("M          Menu");
        System.out.println("SOLVE      Undo all moves and return cube to solved state");
        System.out.println("RANDOMIZE  Shuffle the cube with <n> random moves");
        System.out.println("SIMPLIFY   Shorten and clean up the move history");
        System.out.println("=========================");
    }

    public static void main(String[] args) throws Exception {
        String[][][] SOLVED = deepCopy(SOLVED_TEMPLATE);

        PrintCube.printCubeBlocks(SOLVED);

        Scanner scn = new Scanner(System.in);

        int argsIndex = 0;

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
                case "U'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "U'");
                    break;
                case "U2":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "U2");
                    break;
                case "D":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "D");
                    break;
                case "D'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "D'");
                    break;
                case "D2":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "D2");
                    break;
                case "R":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "R");
                    break;
                case "R'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "R'");
                    break;
                case "R2":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "R2");
                    break;
                case "L":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "L");
                    break;
                case "L'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "L'");
                    break;
                case "L2":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "L2");
                    break;
                case "F":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "F");
                    break;
                case "F'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "F'");
                    break;
                case "F2":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "F2");
                    break;
                case "B":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "B");
                    break;
                case "B'":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "B'");
                    break;
                case "B2":
                    moves.push(move.toUpperCase());
                    Moves.applyMove(SOLVED, "B2");
                    break;
                case "SOLVE":
                    List<String> sln = Solve.solve(moves);
                    Moves.applyMoves(SOLVED, sln);
                    PrintCube.printCubeBlocks(SOLVED);
                    break;
                case "RANDOMIZE": 
                    int numMoves = -1;
                    do {  
                        System.out.println("Enter number of moves");
                        String input = scn.nextLine().trim();
                        if (input.isEmpty()) {
                            System.out.println("Error: Please input a number.");
                            continue;
                        }
                        
                        int n;
                        try {
                            n = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: '" + input + "' is not a valid number.");
                            continue; 
                        }
                
                        if (n <= 0) {
                            System.out.println("Error: Number of moves must be positive.");
                            continue; 
                        }
                
                        if (n > 1000) {
                            System.out.println("Warning: Large number of moves may take a while. Continue? (y/n)");
                            String confirm = scn.nextLine().trim().toLowerCase();
                            if (!confirm.equals("y") && !confirm.equals("yes")) {
                                continue; 
                            }
                        }
                        numMoves = n;
                        break;
                    } while (numMoves < 0);
                    if (numMoves > 0) {
                        Randomizer.randomize(SOLVED, numMoves, moves);
                    }
                    break;
                case "SIMPLIFY":
                    if (moves.isEmpty()) {
                        System.out.println("No moves to simplify.");
                        break;
                    }
                
                    String[] movesArr = moves.toArray(new String[0]);
                    System.out.println("Moves: " + movesArr.length);
                
                    String[] results = Simplifier.simplify(movesArr);
                
                    System.out.println("Simplified moves:");
                    for (String m : results) System.out.print(m + " ");
                    System.out.println();
                    System.out.println("Moves: " + results.length);
                    System.out.println();
                
                    // yes/no loop
                    String shouldSolve;
                    while (true) {
                        System.out.print("Do you want to solve using simplified moves? (y/n): ");
                        shouldSolve = scn.nextLine().trim().toLowerCase();
                        if (shouldSolve.equals("y") || shouldSolve.equals("yes")
                         || shouldSolve.equals("n") || shouldSolve.equals("no")
                         || shouldSolve.isEmpty()) { // treat empty as "no"
                            break;
                        }
                        System.out.println("Please enter 'y' or 'n'.");
                    }
                
                    if (shouldSolve.equals("y") || shouldSolve.equals("yes")) {
                        if (results.length == 0) {
                            System.out.println("Nothing to solve (no moves after simplification).");
                            break;
                        }
                        Stack<String> resultsStack = new Stack<>();
                        for (String result : results) resultsStack.push(result);
                
                        sln = Solve.solve(resultsStack);
                        Moves.applyMoves(SOLVED, sln);
                
                        resultsStack.clear();
                        moves.clear();
                        PrintCube.printCubeBlocks(SOLVED);
                    }
                    break;
                case "E":
                    PrintCube.printCubeBlocks(SOLVED);
                    break;
                case "M":
                    printMenu();
                    break;
                case "Q":
                    scn.close();
                    return;
                default:
                    System.out.println("Invalid command: '" + move + "'");
                    System.out.println("Type 'M' for menu or use valid moves: U, U', U2, D, D', D2, R, R', R2, L, L', L2, F, F', F2, B, B', B2");
                    break;
            }
        }
    }
}
