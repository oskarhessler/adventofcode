import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day2problem2 {
    public static void main(String[] args) throws FileNotFoundException {
        String filepath = "C:\\Users\\Admin\\Desktop\\oskarhessler\\adventofcode\\Day 2\\infile.txt";
        int counter = 0;

        // Open the input file with a try-with-resources statement
        try (Scanner lineScanner = new Scanner(new File(filepath))) {
            while (lineScanner.hasNextLine()) {
                // Create a list for the current line
                ArrayList<Integer> tempList = createList(new Scanner(lineScanner.nextLine()));
                if (!tempList.isEmpty()) {
                    // Trigger relation check and count valid lines
                    counter += relationTrigger(tempList);
                }
            }
        }
        System.out.println("Count of valid lines: " + counter);
    }

    public static ArrayList<Integer> createList(Scanner scan) {
        ArrayList<Integer> temp = new ArrayList<>();
        while (scan.hasNextInt()) {
            temp.add(scan.nextInt());
        }
        return temp;
    }

    public static int relationTrigger(ArrayList<Integer> temp) {
        // Check if the sequence is safe without modification
        if (isValidSequence(temp)) {
            return 1;
        }

        // Try removing one element and check if the sequence becomes valid
        for (int i = 0; i < temp.size(); i++) {
            ArrayList<Integer> modifiedList = new ArrayList<>(temp);
            modifiedList.remove(i);

            if (isValidSequence(modifiedList)) {
                return 1; // If any modified sequence is valid, count as valid
            }
        }

        return 0; // Fail if no valid sequence is found
    }

    // Method to check if a sequence is valid (either strictly increasing or decreasing with a difference of at most 3)
    public static boolean isValidSequence(ArrayList<Integer> temp) {
        // Check for increasing sequence with no difference greater than 3
        if (isIncreasing(temp)) {
            return true;
        }

        // Check for decreasing sequence with no difference greater than 3
        if (isDecreasing(temp)) {
            return true;
        }

        return false;
    }

    // Check if the sequence is strictly increasing with difference <= 3
    public static boolean isIncreasing(ArrayList<Integer> temp) {
        for (int i = 0; i < temp.size() - 1; i++) {
            if (temp.get(i) >= temp.get(i + 1) || (temp.get(i + 1) - temp.get(i)) > 3) {
                return false;
            }
        }
        return true;
    }

    // Check if the sequence is strictly decreasing with difference <= 3
    public static boolean isDecreasing(ArrayList<Integer> temp) {
        for (int i = 0; i < temp.size() - 1; i++) {
            if (temp.get(i) <= temp.get(i + 1) || (temp.get(i) - temp.get(i + 1)) > 3) {
                return false;
            }
        }
        return true;
    }
}
