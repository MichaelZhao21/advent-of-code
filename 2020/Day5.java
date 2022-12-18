package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day5 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day5.in"));
        final int n = 945;
        String[] inputs = new String[n];
        for (int i = 0; i < n; i++) {
            inputs[i] = f.readLine();
        }
        f.close();

        int[] seats = new int[1024];
        int maxID = 0;
        for (String s : inputs) {
            char[] cols = s.substring(0, 7).toCharArray();
            char[] rows = s.substring(7).toCharArray();
            int min = 0;
            int max = 127;
            for (char c : cols) {
                if (c == 'F') max = (max + min) / 2;
                else min = (int) Math.ceil((max + min) / 2.0);
            }
            int currID = min * 8;
            min = 0;
            max = 7;
            for (char r : rows) {
                if (r == 'L') max = (max + min) / 2;
                else min = (int) Math.ceil((max + min) / 2.0);
            }
            currID += min;
            seats[currID]++;

            // Part 1
            if (maxID < currID)
                maxID = currID;
        }
        System.out.println(maxID);

        for (int i = 1; i < 1023; i++) {
            if (seats[i - 1] == 1 && seats[i + 1] == 1 && seats[i] == 0) {
                System.out.println(i);
            }
        }
    }
}
