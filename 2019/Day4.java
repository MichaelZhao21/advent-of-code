package y_2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2019/day4.in"));
        String[] rawIn = f.readLine().split("-");
        f.close();
        int min = Integer.parseInt(rawIn[0]);
        int max = Integer.parseInt(rawIn[1]) + 1;

        int pass = 0;
        for (int i = min; i < max; i++) {
            char[] curr = Integer.toString(i).toCharArray();
            boolean good = true;
            boolean twice = false;
            int dupLen = 0;

            for (int j = 1; j < curr.length; j++) {
                if (curr[j] < curr[j - 1]) {
                    good = false;
                    break;
                }
                if (curr[j] == curr[j - 1]) dupLen++;
                else {
                    if (dupLen == 1) twice = true;
                    else if (dupLen > 1) {
                        dupLen = 0;
                    }
                }
            }
            if (dupLen == 1) twice = true;

            if (good && twice) pass++;
        }
        System.out.println(pass);
    }
}
