public class XMAS_Test {
    public static void main(String[] args) {
        // Define a small test grid (the grid from above)
        char[][] grid = {
            {'.', 'M', '.', 'S', '.', '.', '.', '.'},
            {'.', '.', 'A', '.', 'M', 'S', '.', '.'},
            {'.', 'M', '.', 'S', '.', '.', 'M', 'A', '.'},
            {'.', '.', 'A', '.', 'A', 'M', 'S', 'M', '.'},
            {'.', 'M', '.', 'S', '.', 'M', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'S', '.', 'S', '.', 'S', '.', 'S', '.'},
            {'.', 'A', '.', 'A', '.', 'A', '.', '.'},
            {'M', '.', 'M', '.', 'M', '.', 'M', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'}
        };

        // Print the grid for visualization
        System.out.println("Test Grid:");
        for (char[] row : grid) {
            System.out.println(new String(row));
        }

        // Count occurrences of X-MAS patterns
        int count = countXMAS(grid);
        System.out.println("Total occurrences of X-MAS: " + count);
    }

    private static int countXMAS(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // Iterate through every cell in the grid as the center of an X-MAS
        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                // Check if this cell is the center of an X-MAS
                if (grid[row][col] == 'A') {
                    count += countXMASAtCenter(grid, row, col);
                }
            }
        }
        return count;
    }

    private static int countXMASAtCenter(char[][] grid, int row, int col) {
        int count = 0;

        // Check all combinations of top-left and bottom-right MAS
        if (isMAS(grid, row - 1, col - 1, -1, -1, true) && isMAS(grid, row + 1, col + 1, 1, 1, true)) count++; // M.S and M.S
        if (isMAS(grid, row - 1, col - 1, -1, -1, false) && isMAS(grid, row + 1, col + 1, 1, 1, false)) count++; // S.M and S.M

        // Check all combinations of top-right and bottom-left MAS
        if (isMAS(grid, row - 1, col + 1, -1, 1, true) && isMAS(grid, row + 1, col - 1, 1, -1, true)) count++; // M.S and M.S
        if (isMAS(grid, row - 1, col + 1, -1, 1, false) && isMAS(grid, row + 1, col - 1, 1, -1, false)) count++; // S.M and S.M

        return count;
    }

    private static boolean isMAS(char[][] grid, int startRow, int startCol, int rowStep, int colStep, boolean forward) {
        // Check MAS pattern starting from (startRow, startCol) in a given direction
        int rows = grid.length;
        int cols = grid[0].length;

        // Ensure valid bounds
        int endRow = startRow + 2 * rowStep;
        int endCol = startCol + 2 * colStep;
        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols || 
            endRow < 0 || endRow >= rows || endCol < 0 || endCol >= cols) {
            return false; // Out of bounds
        }

        if (forward) {
            // Forward MAS: M.A.S
            return grid[startRow][startCol] == 'M' &&
                   grid[startRow + rowStep][startCol + colStep] == 'A' &&
                   grid[endRow][endCol] == 'S';
        } else {
            // Backward MAS: S.A.M
            return grid[startRow][startCol] == 'S' &&
                   grid[startRow + rowStep][startCol + colStep] == 'A' &&
                   grid[endRow][endCol] == 'M';
        }
    }
}
