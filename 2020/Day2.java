package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day2.in"));
        final int n = 1000;

        int valid = 0;
        // Part 1
//        for (int i = 0; i < n; i++) {
//            String[] params = f.readLine().split(" ");
//            String[] numsRaw = params[0].split("-");
//            int min = Integer.parseInt(numsRaw[0]);
//            int max = Integer.parseInt(numsRaw[1]);
//            char q = params[1].charAt(0);
//            char[] letters = params[2].toCharArray();
//            int count = 0;
//            for (char c : letters) {
//                if (c == q) count++;
//            }
//            if (count >= min && count <= max) valid++;
//        }

        // Part 2
        for (int i = 0; i < n; i++) {
            String[] params = f.readLine().split(" ");
            String[] numsRaw = params[0].split("-");
            int a = Integer.parseInt(numsRaw[0]);
            int b = Integer.parseInt(numsRaw[1]);
            char q = params[1].charAt(0);
            char[] letters = params[2].toCharArray();
            boolean matchA = letters[a - 1] == q;
            boolean matchB = letters[b - 1] == q;
            if (matchA ^ matchB) valid++;
        }

        System.out.println(valid);
        f.close();
    }
}
