class Solution {
    private void dfs(int[][] grid, int m, int n, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 1) return;
        grid[r][c] = 1;
        dfs(grid, m, n, r + 1, c);
        dfs(grid, m, n, r - 1, c);
        dfs(grid, m, n, r, c + 1);
        dfs(grid, m, n, r, c - 1);
    }

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) dfs(grid, m, n, i, 0);
            if (grid[i][n - 1] == 0) dfs(grid, m, n, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0) dfs(grid, m, n, 0, j);
            if (grid[m - 1][j] == 0) dfs(grid, m, n, m - 1, j);
        }

        int count = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    dfs(grid, m, n, i, j);
                }
            }
        }
        return count;
    }
}