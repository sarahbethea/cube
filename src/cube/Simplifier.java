package cube;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Simplifier {
    // need to save moves from command line 

    private static List<String> toList(String[] moveArr) {
        List<String> moveList = new ArrayList<>(moveArr.length);
        for (String move : moveArr) {
            if (move == null) continue;
            move = move.trim().toUpperCase();
            moveList.add(move);
        }
        return moveList;
    }

    private static String[] toArray(List<String> moveList) {
        return moveList.toArray(new String[moveList.size()]); 
    }

    private static String getFace(String move) {
        // Get just the face without prime 
        // "U" -> "U", "U'" -> "U"
        return move.substring(0,1);
    }

    private static boolean isPrime(String move) {
        // return true if prime
        return move.endsWith("'");
    }

    private static boolean isSameFace(String move1, String move2) {
        // return true if both moves have same face
        return getFace(move1).equals(getFace(move2));
    }

    private static boolean areOpposites(String move1, String move2) {
        // return true if same face and only one is prime 
        return isSameFace(move1, move2) && (isPrime(move1) != isPrime(move2));
    }

    private static boolean isDouble(String move) {
        return move.endsWith("2");
    }

    private static String flipPrime(String move) {
        char face = Character.toUpperCase(move.charAt(0));
        boolean prime = move.length() >= 2 && move.charAt(1) == '\'';
        return prime ? ("" + face) : (face + "'");
    }

    private static List<String> cancelOutConsecutiveDoubles(List<String> moves) {
        List<String> results = new ArrayList<>(moves.size());

        for (String move : moves) {
            if (!results.isEmpty() && move.equals(results.get(results.size() - 1))) {
                // consecutive identical -> cancel the pair
                results.remove(results.size() - 1);
            } else {
                results.add(move);
            }   
        }
        return results;
    }

    public static List<String> simplifyMoves(List<String> moves) {
        List<String> results = new ArrayList<>(moves.size());

        // For first pass, push move to results list
        for (String move : moves) {
            if (results.isEmpty()) {
                results.add(move);
                continue;
            }
            
            // Get the previous move of results list 
            String lastMove = results.get(results.size() - 1);

            if (isSameFace(lastMove, move)) {
                // Rule A: identical quarter-turns make a double turn
                //   "U" + "U"   -> "U2"
                //   "U'" + "U'" -> "U2"
                if (!isDouble(lastMove) && !isDouble(move) && lastMove.equals(move)) {
                    results.set(results.size() - 1, getFace(move) + "2");
                    continue;
                }

                // Rule B: opposite quarter-turns cancel out
                //   "U" + "U'" or "U'" + "U" -> remove both
                if (areOpposites(lastMove, move)) {
                    results.remove(results.size() - 1);
                    continue;
                }

                // Rule C: one double + one quarter on same face -> opposite of the quarter
                // "U2" + "U'" -> "U"
                if (isDouble(lastMove) ^ isDouble(move)) {          // exactly one is a double
                    String single = isDouble(lastMove) ? move : lastMove; // the quarter move
                    String flipped = flipPrime(single);                      // flip its prime
                    results.set(results.size() - 1, flipped);                // replace the pair with flipped single
                    continue;
                }

                // Same face but not to be combined ("U2" "U")
                results.add(move);
                continue;
            }
            // Difference faces
            results.add(move);

        }
        return results;
    }

    public static String[] simplify(String[] moves) {
        List<String> moveList = toList(moves);
        List<String> firstPass = simplifyMoves(moveList);
        // Go over firstPass to cancel out doubles
        List<String> results = cancelOutConsecutiveDoubles(firstPass);

        return toArray(results);

    }
}
