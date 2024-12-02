import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day2problem1 {
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
        int a = temp.get(0);
        int b = temp.get(1);

        if (a > b) { // Decreasing order check
            return smallerCheck(temp);
        } else if (b > a) { // Increasing order check
            return biggerCheck(temp);
        } else {
            return 0; // Fail if first two elements are equal
        }
    }

    public static int biggerCheck(ArrayList<Integer> temp) {
        for (int i = 0; i < temp.size() - 1; i++) {
            if (temp.get(i) >= temp.get(i + 1) || (temp.get(i + 1) - temp.get(i)) > 3) {
                return 0; // Fail for duplicates or increment > 3
            }
        }
        return 1; // Valid increasing sequence
    }

    public static int smallerCheck(ArrayList<Integer> temp) {
        for (int i = 0; i < temp.size() - 1; i++) {
            if (temp.get(i) <= temp.get(i + 1) || (temp.get(i) - temp.get(i + 1)) > 3) {
                return 0; // Fail for duplicates or decrement > 3
            }
        }
        return 1; // Valid decreasing sequence
    }
}
