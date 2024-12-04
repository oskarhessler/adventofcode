import java.io.*;
import java.util.*;

public class Day4Problem2 {
    public static void main(String[] args) {
        // File path to the input file
        String filename = "C:\\Users\\Admin\\Desktop\\oskarhessler\\adventofcode\\Day 4\\infile.txt";
        List<String> gridLines = new ArrayList<>();

        // Read the grid from the file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                gridLines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Convert the grid lines to a 2D character array
        char[][] grid = new char[gridLines.size()][];
        for (int i = 0; i < gridLines.size(); i++) {
            grid[i] = gridLines.get(i).toCharArray();
        }

        // Count occurrences of X-MAS
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

        // Check top-left to bottom-right MAS (forward or reversed)
        boolean topLeftForward = isMAS(grid, row - 1, col - 1, true);
        boolean topLeftBackward = isMAS(grid, row - 1, col - 1, false);

        // Check top-right to bottom-left MAS (forward or reversed)
        boolean topRightForward = isMAS(grid, row - 1, col + 1, true);
        boolean topRightBackward = isMAS(grid, row - 1, col + 1, false);

        // Count valid combinations of top-left and top-right arms
        if (topLeftForward && topRightForward) count++;
        if (topLeftForward && topRightBackward) count++;
        if (topLeftBackward && topRightForward) count++;
        if (topLeftBackward && topRightBackward) count++;

        return count;
    }

    private static boolean isMAS(char[][] grid, int row, int col, boolean forward) {
        // Check MAS pattern starting at (row, col), either forward or backward
        if (row < 0 || row + 2 >= grid.length || col < 0 || col + 2 >= grid[0].length) {
            return false; // Out of bounds
        }
        if (forward) {
            return grid[row][col] == 'M' && grid[row + 2][col + 2] == 'S' && grid[row + 1][col + 1] == 'A';
        } else {
            return grid[row][col] == 'S' && grid[row + 2][col + 2] == 'M' && grid[row + 1][col + 1] == 'A';
        }
    }
}
