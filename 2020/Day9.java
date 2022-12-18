package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day9 {
    final static int n = 1000;
    final static int pre = 25;
    static long[] nums = new long[n];
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day9.in"));
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(f.readLine());
        }
        f.close();

        long bad = 0;

        // Part 1
        for (int i = pre; i < n; i++) {
            if (!check(i)) {
                bad = nums[i];
                break;
            }
        }
        System.out.println(bad);

        int min = 0;
        int max = 0;

        // Part 2
        for (int i = 0; i < n; i++) {
            long currSum = nums[i];
            int j;
            for (j = i + 1; j < n && currSum < bad; j++) {
                currSum += nums[j];
            }
            if (currSum == bad) {
                min = i;
                max = j;
                break;
            }
        }
        if (max == 0) System.out.println("uh oh");

        long[] set = Arrays.copyOfRange(nums, min, max);
        Arrays.sort(set);
        System.out.println(Arrays.toString(set));
        System.out.println(set[0] + set[set.length - 1]);
    }

    public static boolean check(int index) {
        for (int i = index - pre; i < index; i++) {
            for (int j = index - pre; j < index; j++) {
                if (i != j && nums[i] + nums[j] == nums[index]) return true;
            }
        }
        return false;
    }
}
