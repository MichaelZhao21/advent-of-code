package y_2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2019/day1.in"));

        final int n = 100;
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(f.readLine());
        }
        f.close();

        // Part 1
        long sum = 0;
        for (int i : nums) {
            sum += Math.floor(i / 3.0) - 2;
        }
        System.out.println(sum);

        // Part 2
        sum = 0;
        for (int i : nums) {
            sum += calcFuel(i);
        }
        System.out.println(sum);
    }

    public static int calcFuel(int load) {
        int newF = (int) Math.floor(load / 3.0) - 2;
        if (newF <= 0) return 0;
        return newF + calcFuel((int) newF);
    }
}
