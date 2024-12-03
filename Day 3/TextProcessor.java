import java.io.*;
import java.util.*;
import java.util.regex.*;

public class TextProcessor {

    public static void main(String[] args) {
        // Define the path to the input file
        String filepath = "C:\\Users\\Admin\\Desktop\\oskarhessler\\adventofcode\\Day 3\\infile.txt";
        
        // Read the entire file content into a single string
        String inputText = readFile(filepath);

        // If file reading was successful, proceed with processing
        if (inputText != null) {
            // Call the function to process the input and create the output file
            rewriteInstructions(inputText, "output.txt");
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

    // Method to process the input and write the output
    public static void rewriteInstructions(String inputText, String outputFilename) {
        // Regular expressions for matching mul(), do(), and don't()
        String mulPattern = "mul\\(\\d+,\\d+\\)";
        String doPattern = "do\\(\\)";
        String dontPattern = "don't\\(\\)";

        // Create a list to store the found matches
        List<String> outputLines = new ArrayList<>();

        // Combine mul(), do(), and don't() patterns into one regex
        String combinedPattern = mulPattern + "|" + doPattern + "|" + dontPattern;
        Pattern combined = Pattern.compile(combinedPattern);

        // Create a Matcher for the combined pattern
        Matcher matcher = combined.matcher(inputText);

        // Process all matches in the input text
        while (matcher.find()) {
            // Add the matched command to the output list
            outputLines.add(matcher.group());
        }

        // Write the output to the specified file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (String line : outputLines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("The instructions have been written to '" + outputFilename + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
