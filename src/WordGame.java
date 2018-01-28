public class WordGame {
    public static void main(String[] args) {
        //char[][] board = {{ 'a', 'b', 'c', 'e'}, { 's', 'f', 'c', 's'}, { 'a', 'd', 'e', 'e'}};
        char[][] board = {{ 'a', 'b'}};
        String word = "ba";

        //String word = "abcced";
        //String word = "see";
        //String word = "abcb";
        System.out.println(exists(board, word));
    }

    public static boolean exists(char[][] board, String word) {
        if (word == null) {
            return false; // alternatively can throw exception
        }
        if (word == "") {
            return true;
        }
        // visited array
        boolean[][] visited = new boolean[board.length][board[0].length];

        boolean matched = false;
        // check each cell in board to see if char at board's cell
        // matches first character, if yes start recursive call
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    matched = checkRecur(board, i, j, word, 1, visited);
                    if (matched) {
                        return matched;
                    } else {
                        visited[i][j] = false;
                    }
                }
            }
        }
        return matched; // false
    }

    public static boolean checkRecur(char[][] board, int x, int y, String word, int index, boolean[][] visited) {
        // base case
        if (index == word.length()) {
            return true;
        }

        // create the directions array to navigate the board
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {

            int col = board[0].length;
            int row = board.length;

            if (x + dir[0] >= 0 ) {
                System.out.println("Row >=0 valid");
            } else {
                System.out.println("Row >=0 invalid");
            }


            if (x + dir[0] < board.length ) {
                System.out.println("< row valid");
            } else {
                System.out.println("< row invalid");
            }

            if (y + dir[1] >= 0 ) {
                System.out.println("Col >=0 valid");
            } else {
                System.out.println("Col >=0 invalid");
            }


            if (y + dir[1] < board[0].length ) {
                System.out.println("< col valid");
            } else {
                System.out.println("< col invalid");
            }

            if (x + dir[0] >= 0 && x + dir[0] < board.length && y + dir[1] >= 0 && y + dir[1] < board[0].length
                    && !visited[x + dir[0]][y + dir[1]]
                    && board[x + dir[0]][y + dir[1]] == word.charAt(index)) {

                visited[x + dir[0]][y + dir[1]] = true;

                boolean matched = checkRecur(board, x + dir[0], y + dir[1], word, index + 1, visited);
                if (matched) {
                    return true;
                } else {
                    visited[x + dir[0]][y + dir[1]] = false;
                }
            }
        }
        return false;
    }
}
