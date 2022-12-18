package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day10 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day10.in"));
        final int n = 108;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(f.readLine());
        }
        f.close();
        Arrays.sort(nums);

        // Part 1
//        int one = 0;
//        int three = 0;
//        for (int i = 0; i < n; i++) {
//            if (nums[i + 1] - nums[i] == 1) one++;
//            else if (nums[i + 1] - nums[i] == 3) three++;
//        }
//        System.out.println(one * three);

        // Part 2
        long[] count = new long[n];
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            while (j < n && nums[j] - nums[i] < 4) {
                count[i]++;
                j++;
            }
        }
        count[n - 1] = 1;
        long[] totals = new long[n];
        totals[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < count[i]; j++) {
                totals[i] += totals[i + j + 1];
            }
        }
        System.out.println(totals[0]);
    }
}
