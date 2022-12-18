import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day1.in"));
        final int n = 200;
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(f.readLine());
        }
        f.close();

        // Part 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i] + nums[j] == 2020) System.out.println(nums[i] * nums[j]);
            }
        }

        // Part 2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 2020) System.out.println(nums[i] * nums[j] * nums[k]);
                }
            }
        }
    }
}
