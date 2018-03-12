import java.util.LinkedList;
import java.util.Queue;

// notes about Breadth first search : always mark and put on the queue for BFS
public class NumberOfIslandsBfs {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int count = 0;

        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {

                // following check when true, starts new BFS at grid[r][c] implying
                // a new island (not reachable from other 1 cells in grid) has been found
                if (grid[r][c] == '1') {

                    // count is changed - meaning a different island has been found
                    // every time numIslands / bfs method is invoked
                    count +=1;

                    // mark as visited by changing the cell's value from 1 to 0
                    grid[r][c] = '0';

                    // neighbors is a queue of integers,
                    // we have to deduce the row # and col # from each integer
                    // we dequeue from neighbors
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);

                    while(!neighbors.isEmpty()) {

                        int id = neighbors.remove();

                        int row = id / nc;
                        int col = id % nc;

                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            // mark as visited by changing the cell's value from 1 to 0
                            grid[row - 1][col] = '0';
                            neighbors.add((row-1) * c + col);
                        }

                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            // mark as visited by changing the cell's value from 1 to 0
                            grid[row + 1][col] = '0';
                            neighbors.add((row+1) * c + col);
                        }

                        if (col - 1 >= 0 && grid[r][col - 1] == '1') {
                            // mark as visited by changing the cell's value from 1 to 0
                            grid[r][col - 1] = '0';
                            neighbors.add(row * nc + col - 1);
                        }

                        if (col + 1 < nc && grid[r][col + 1] == '1') {
                            // mark as visited by changing the cell's value from 1 to 0
                            grid[r][col + 1] = '0';
                            neighbors.add(row * nc + col + 1);
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        NumberOfIslandsBfs ni = new NumberOfIslandsBfs();
        char[][] grid = {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '0', '0'}, {'1', '0', '1'}};
        System.out.println(ni.numIslands(grid));
    }
}
