import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Read in file
        BufferedReader f = new BufferedReader(new FileReader("day1.in"));
        int n = 1000;
        int[] one = new int[n];
        int[] two = new int[n];

        for (int i = 0; i < n; i++) {
            String s = f.readLine();
            String[] ss = s.split("   ");
            one[i] = Integer.parseInt(ss[0]);
            two[i] = Integer.parseInt(ss[1]);
        }

        f.close();

        // Part 1
        // Sort two lists
        Arrays.sort(one);
        Arrays.sort(two);
        long diff = 0;
        for (int i = 0; i < n; i++) {
            diff += (long) Math.abs(one[i] - two[i]);
        }

        System.out.println(diff);

        // Part 2
        long total = 0;
        for (int i = 0; i < n; i++) {
            int curr = one[i];
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (curr == two[j]) {
                    count++;
                }
            }
            total += count * curr;
        }

        System.out.println(total);
    }
}
