import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class day3problem1 {
    public static void main(String[] args) {
        // File paths
        String filepath1 = "C:\\Users\\alvah\\OneDrive\\Skrivbord\\Advent of Code\\Day 1\\infile.txt";
        String filepath2 = "C:\\Users\\Admin\\Desktop\\oskarhessler\\adventofcode\\Day 3\\infile.txt";

        // Regex mönster
        String pattern1 = "mul\\((\\d+),(\\d+)\\)";
        Pattern regex1 = Pattern.compile(pattern1);

        int totSum = 0; 

        try (Scanner scan = new Scanner(new File(filepath2))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Matcher matcher = regex1.matcher(line);

                // Hitta och processa matchningarna
                while (matcher.find()) {
                    // Plocka ut nummer
                    int a = Integer.parseInt(matcher.group(1));
                    int b = Integer.parseInt(matcher.group(2));

                    // Beräkna produkten och addera till totalen
                    int product = a * b;
                    totSum += product;
                }
            }

            // Output totSum
            System.out.println("Summa av produkterna: " + totSum);

        } catch (FileNotFoundException ex) {
            System.out.println("Filen hittades ej: " + filepath2);
        }
    }
}
