import java.io.*;
import java.util.*;

public class Day4Problem1 {
    // Define all eight possible directions as row and column deltas
    private static final int[][] DIRECTIONS = {
        {0, 1},   // Right
        {0, -1},  // Left
        {1, 0},   // Down
        {-1, 0},  // Up
        {1, 1},   // Down-Right
        {1, -1},  // Down-Left
        {-1, 1},  // Up-Right
        {-1, -1}  // Up-Left
    };

    public static void main(String[] args) {
        // File containing the grid
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

        // Target word
        String target = "XMAS";

        // Count occurrences of the word
        int count = countOccurrences(grid, target);
        System.out.println("Total occurrences of XMAS: " + count);
    }

    private static int countOccurrences(char[][] grid, String target) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // Loop through each cell in the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Check in all directions from the current cell
                for (int[] dir : DIRECTIONS) {
                    if (checkDirection(grid, row, col, dir, target)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean checkDirection(char[][] grid, int row, int col, int[] dir, String target) {
        int rows = grid.length;
        int cols = grid[0].length;
        int len = target.length();

        // Check if the word fits in the current direction
        for (int i = 0; i < len; i++) {
            int newRow = row + i * dir[0];
            int newCol = col + i * dir[1];
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
