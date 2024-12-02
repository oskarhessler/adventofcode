import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class problem1 {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws Exception {

        // Create two ArrayLists to store integers
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        int count = 0;
        String filepath = "C:\\Users\\Admin\\Desktop\\oskarhessler\\adventofcode\\Day 2\\infile2.txt";
        //String filepath2 = "C:\\Users\\alvah\\OneDrive\\Skrivbord\\Advent of Code\\Day 1\\infile.txt";
        // Open the input file
        Scanner scan = new Scanner(new File(filepath));
        // Open the input file

        
        while (scan.hasNextInt()) {
            a.add(scan.nextInt()); // First number goes to list A
            b.add(scan.nextInt()); // Second number goes to list B
        }
        scan.close();
        
        // Sort both lists
        Collections.sort(a);
        Collections.sort(b);

        for (int i = 0; i < a.size(); i++) {
                count += Math.abs(a.get(i) - b.get(i)); // Add the difference (absolute(a[i] - b[i]) to count
        }

        
        // Output the final count
        System.out.println(count);
    }
}


