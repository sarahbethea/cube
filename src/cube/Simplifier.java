// r + r' cance
// u + u -> u2
// f2 + f' -> f
// L + L + L -> L'
// b2 + b2 cancel
// R + U + U' + R' -> U U' cancels in the middle then R R' cancels
// same 

package cube;

import java.util.ArrayList;
import java.util.List;

public class Simplifier {
    // need to save moves from command line 

    static List<String> toList(String[] moveArr) {
        List<String> moveList = new ArrayList<>(moveArr.length);
        for (String move : moveArr) {
            if (move == null) continue;
            move.trim().toUpperCase();
            moveList.add(move);
        }
        return moveList;
    }

    static String[] toArray(List<String> moveList) {
        return moveList.toArray(new String[moveList.size()]); 
    }

    static String getFace(String move) {
        // Get just the face without prime 
        // "U" -> "U", "U'" -> "U"
        return move.substring(0,1);
    }

    static boolean isPrime(String move) {
        // return true if prime
        return move.endsWith("'");
    }

    static boolean isSameFace(String move1, String move2) {
        // return true if both moves have same face
        return getFace(move1).equals(getFace(move2));
    }

    static boolean areOpposites(String move1, String move2) {
        // return true if same face and only one is prime 
        return isSameFace(move1, move2) && (isPrime(move1) != isPrime(move2));
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

            // If move cancels out last move, remove last move and dont add move to list
            if (areOpposites(lastMove, move)) {
                results.remove(results.size() - 1);
            } else {
                results.add(move);
            }
        }
        return results;
    }


    public static String[] simplify(String[] moves) {
        List<String> moveList = toList(moves);
        List<String> results = simplifyMoves(moveList);

        // convert back to String[]
        return toArray(results);

    }
}
