public class App {

    static void printCube(String[][][] cube) {
        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 3; c++) {
                for (int r = 0; r < 3; r++) {
                    System.out.print(cube[f][c][r]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws Exception {

        String[][][] SOLVED = {
            {
                {"w", "w", "w"},
                {"w", "w", "w"},
                {"w", "w", "w"}
            },

            {
                {"b", "b", "b"},
                {"b", "b", "b"},
                {"b", "b", "b"}
            },

            {
                {"y", "y", "y"}, 
                {"y", "y", "y"}, 
                {"y", "y", "y"}
            },

            {
                {"g", "g", "g"}, 
                {"g", "g", "g"},
                {"g", "g", "g"}
            }, 

            {
                {"r", "r", "r"}, 
                {"r", "r", "r"}, 
                {"r", "r", "r"}
            },

            {
                {"o", "o", "o"}, 
                {"o", "o", "o"}, 
                {"o", "o", "o"}
            }
        };


        String[][] SOLVED2D = {
            {"w", "w", "w",
            "w", "w", "w",
            "w", "w", "w"},

            {"b", "b", "b",
            "b", "b", "b",
            "b", "b", "b"},

            {"y", "y", "y", 
            "y", "y", "y", 
            "y", "y", "y"},

            {"g", "g", "g", 
            "g", "g", "g", 
            "g", "g", "g"},

            {"r", "r", "r", 
            "r", "r", "r", 
            "r", "r", "r"},

            {"o", "o", "o", 
            "o", "o", "o", 
            "o", "o", "o"}
        };

        // Whole cube jagged array to play with
        String[][] JAGGED_SOLVED = {
            {"w", "w", "w", "o", "o", "o", "r", "r", "r"},
            {"w", "w", "w", "o", "o", "o", "r", "r", "r"},
            {"w", "w", "w", "o", "o", "o", "r", "r", "r"},
            {"b", "b", "b"},
            {"b", "b", "b"},
            {"b", "b", "b"},
            {"g", "g", "g"},
            {"g", "g", "g"},
            {"g", "g", "g"},
            {"y", "y", "y"},
            {"y", "y", "y"},
            {"y", "y", "y"},
        };

        printCube(SOLVED);

    
        System.out.println("Hello, World!");
    }
}
