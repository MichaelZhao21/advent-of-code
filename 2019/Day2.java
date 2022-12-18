package y_2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day2 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2019/day2.in"));
        int[] initNums = Arrays.stream(f.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        f.close();
        int index = 0;
        final int n = 100;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[] nums = Arrays.copyOf(initNums, initNums.length);
                nums[1] = i;
                nums[2] = j;
                index = 0;
                while (nums[index] != 99) {
                    if (nums[index] == 1) {
                        nums[nums[index + 3]] = nums[nums[index + 1]] + nums[nums[index + 2]];
                    }
                    else if (nums[index] == 2) {
                        nums[nums[index + 3]] = nums[nums[index + 1]] * nums[nums[index + 2]];
                    }
                    else {
                        System.out.println("uh oh");
                    }
                    index += 4;
                }
                if (nums[0] == 19690720) System.out.println(100 * i + j);
            }
        }
    }
}
