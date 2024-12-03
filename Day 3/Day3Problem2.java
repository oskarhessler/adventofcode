import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Day3Problem2 {

    public static void main(String[] args) {
        // Define the path to the output file (the file with mul(), do(), and don't() commands)
        String filepath = "output.txt";
        
        // Read the entire content of the file
        String inputText = readFile(filepath);
        
        // If reading the file was successful, proceed with processing the commands
        if (inputText != null) {
            // Call the function to process the input text and execute the commands
            int sum = executeCommands(inputText);
            System.out.println("Sum of products: " + sum);
        } else {
            System.out.println("File not found or error reading the file.");
        }
    }

    // Method to read the content of a file into a single string
    public static String readFile(String filepath) {
        StringBuilder content = new StringBuilder();
        try (Scanner scan = new Scanner(new File(filepath))) {
            while (scan.hasNextLine()) {
                content.append(scan.nextLine()).append("\n");
            }
            return content.toString();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;  // Return null if file not found
        }
    }

    // Method to execute the commands and return the sum of products
    public static int executeCommands(String inputText) {
        // Split the input into lines
        String[] lines = inputText.split("\n");
        
        // Initialize variables
        boolean isDoEnabled = true;  // Tracks if we should output multiplication results (starts as enabled)
        int lastResult = 0;  // Stores the result of the last mul(x, y) command
        int sum = 0;  // Stores the sum of the products that should be output
        
        // Process each line
        for (String line : lines) {
            line = line.trim();  // Remove leading/trailing whitespace
            
            if (line.startsWith("mul(")) {
                // Extract the numbers from mul(x, y)
                Matcher mulMatcher = Pattern.compile("mul\\((\\d+),(\\d+)\\)").matcher(line);
                if (mulMatcher.find()) {
                    int x = Integer.parseInt(mulMatcher.group(1));
                    int y = Integer.parseInt(mulMatcher.group(2));
                    lastResult = x * y;  // Multiply x and y
                    
                    // If do() is enabled, add the result to the sum
                    if (isDoEnabled) {
                        sum += lastResult;
                    }
                }
            } else if (line.equals("do()")) {
                // Enable the toggling of output
                isDoEnabled = true;
            } else if (line.equals("don't()")) {
                // Disable the toggling of output
                isDoEnabled = false;
            }
        }
        
        // Return the sum of products
        return sum;
    }
}
