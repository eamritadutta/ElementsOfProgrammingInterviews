// The following program demonstrates how to apply dfs to an array / grid
// Important to understand how DFS can not only be applied to trees but the arrays / grids as well
public class NumberOfIslandsDfs {

    // performing dfs on a grid basically means looking at "one" of the 4 neighbors of the current cell,
    // then if "one" of those neighbors are not visited, looking at "one" of the neighbors of that non-visited
    // neighbor and so on...
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        // mark as visited
        grid[r][c] = '0';

        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c+1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int count = 0;

        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {

                if (grid[r][c] == '1') {
                    // one more island has been found every time dfs has to be invoked
                    // due to disconnected array traversal. Recursive invocations of dfs
                    // imply neighboring 1 cells so count is not increased meaning
                    // no new island has been found
                    count += 1;

                    dfs(grid, r, c);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        NumberOfIslandsDfs ni = new NumberOfIslandsDfs();
        char[][] grid = {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '0', '0'}, {'1', '0', '1'}};
        System.out.println(ni.numIslands(grid));
    }
}
