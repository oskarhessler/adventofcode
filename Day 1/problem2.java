import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class problem2 {

    public static void main(String[] args) {
        // Create two ArrayLists to store integers
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        
        // Define the input file path
        String filePath = "C:/Users/alvah/OneDrive/Skrivbord/Advent of Code/Day 1/infile.txt";
        
        // Open the input file using Scanner
        try (Scanner scan = new Scanner(new File(filePath))) {
            while (scan.hasNextInt()) {
                a.add(scan.nextInt()); // First number goes to list A
                if (scan.hasNextInt()) {
                    b.add(scan.nextInt()); // Second number goes to list B
                }
            }
        } catch (Exception e) {
            // Handle potential errors (e.g., file not found, etc.)
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        
        // Check if the lists are populated (for debugging purposes)
        System.out.println("List A is empty: " + a.isEmpty());
        System.out.println("List B is empty: " + b.isEmpty());

        // Calculate the similarity score
        int totalScore = calculateSimilarityScore(a, b);
        
        // Output the final similarity score
        System.out.println("Total Similarity Score: " + totalScore);
    }

    // Method to calculate the similarity score between two ArrayLists
    public static int calculateSimilarityScore(ArrayList<Integer> a, ArrayList<Integer> b) {
        // Create a HashMap to store the frequency count of numbers in list b
        HashMap<Integer, Integer> bCount = new HashMap<>();

        // Count occurrences of each number in list b
        for (int num : b) {
            bCount.put(num, bCount.getOrDefault(num, 0) + 1);
        }

        // Initialize total similarity score to 0
        int totalScore = 0;

        // Loop through each number in list a
        for (int num : a) {
            // If the number exists in bCount (i.e., it appears in list b)
            if (bCount.containsKey(num)) {
                // Add the score: number * occurrences in b
                totalScore += num * bCount.get(num);
            }
        }

        // Return the total similarity score
        return totalScore;
    }
}
