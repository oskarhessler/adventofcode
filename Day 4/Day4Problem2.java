import java.io.*;
import java.util.*;

public class Day4Problem2 {
    
    public static void main(String[] args) throws IOException {
        // Set up the input file path
        String filename = "C:\\Users\\Admin\\Desktop\\oskarhessler\\adventofcode\\Day 4\\infile.txt";
        
        // Read the file content into a List of strings (simulating the vector of strings)
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        List<String> data = new ArrayList<>();
        
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        reader.close();

        int counta = 0;
        int count = 0;

        // Iterate through each character in the grid and process it
        for (int i = 0; i < data.size(); i++) {
            for (int ii = 0; ii < data.get(i).length(); ii++) {
                if (data.get(i).charAt(ii) == 'A') {
                    counta++;
                    count += x(data, i, ii);
                }
            }
        }
        
        // Print the results
        System.out.println(counta);
        System.out.println(count);
    }

    // This method mimics the x function in C++
    public static int x(List<String> data, int x, int y) {
        String temp1 = "";
        String temp2 = "";

        // Check bounds before accessing data to avoid ArrayIndexOutOfBoundsException
        if (x - 1 >= 0 && y - 1 >= 0 && x + 1 < data.size() && y + 1 < data.get(x + 1).length() && y - 1 < data.get(x + 1).length() && y + 1 < data.get(x + 1).length()) {
            temp1 += data.get(x - 1).charAt(y - 1);
            temp1 += data.get(x).charAt(y);
            temp1 += data.get(x + 1).charAt(y + 1);

            temp2 += data.get(x - 1).charAt(y + 1);
            temp2 += data.get(x).charAt(y);
            temp2 += data.get(x + 1).charAt(y - 1);
        }

        // Check if both strings match "MAS" or "SAM"
        if ((temp1.equals("MAS") || temp1.equals("SAM")) && (temp2.equals("MAS") || temp2.equals("SAM"))) {
            return 1;
        } else {
            System.out.println(temp1);
            System.out.println(temp2);
            System.out.println(x + "  " + y);
            return 0;
        }
    }
}
