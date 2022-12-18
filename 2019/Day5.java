package y_2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2019/day5.in"));
        Scanner scan = new Scanner(System.in);
        int[] nums = Arrays.stream(f.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        f.close();
        int index = 0;
        int increment = 4;
        while (nums[index] != 99) {
            int opCode = nums[index] % 100;
            String temp = Integer.toString(nums[index]);
            char[] modes;
            if (temp.length() <= 2) modes = new char[]{'0', '0', '0', '0'};
            else modes = new StringBuilder(temp.substring(0, temp.length() - 2)).reverse().append("000").toString().toCharArray();
            if (opCode == 1) {
                int n1 = modes[0] == '0' ? nums[nums[index + 1]] : nums[index + 1];
                int n2 = modes[1] == '0' ? nums[nums[index + 2]] : nums[index + 2];
                nums[nums[index + 3]] = n1 + n2;
                increment = 4;
            }
            else if (opCode == 2) {
                int n1 = modes[0] == '0' ? nums[nums[index + 1]] : nums[index + 1];
                int n2 = modes[1] == '0' ? nums[nums[index + 2]] : nums[index + 2];
                nums[nums[index + 3]] = n1 * n2;
                increment = 4;
            }
            else if (opCode == 3) {
                System.out.print("Enter ID: ");
                int n1 = scan.nextInt();
                nums[nums[index + 1]] = n1;
                increment = 2;
            }
            else if (opCode == 4) {
                int n1 = modes[0] == '0' ? nums[nums[index + 1]] : nums[index + 1];
                System.out.println(n1);
                increment = 2;
            }
            else if (opCode == 5) {
                int n1 = modes[0] == '0' ? nums[nums[index + 1]] : nums[index + 1];
                if (n1 != 0) {
                    index = modes[1] == '0' ? nums[nums[index + 2]] : nums[index + 2];
                    increment = 0;
                }
                else increment = 3;
            }
            else if (opCode == 6) {
                int n1 = modes[0] == '0' ? nums[nums[index + 1]] : nums[index + 1];
                if (n1 == 0) {
                    index = modes[1] == '0' ? nums[nums[index + 2]] : nums[index + 2];
                    increment = 0;
                }
                else increment = 3;
            }
            else if (opCode == 7) {
                int n1 = modes[0] == '0' ? nums[nums[index + 1]] : nums[index + 1];
                int n2 = modes[1] == '0' ? nums[nums[index + 2]] : nums[index + 2];
                nums[nums[index + 3]] = n1 < n2 ? 1 : 0;
                increment = 4;
            }
            else if (opCode == 8) {
                int n1 = modes[0] == '0' ? nums[nums[index + 1]] : nums[index + 1];
                int n2 = modes[1] == '0' ? nums[nums[index + 2]] : nums[index + 2];
                nums[nums[index + 3]] = n1 == n2 ? 1 : 0;
                increment = 4;
            }
            else {
                System.exit(1);
            }
            index += increment;
        }
        scan.close();
    }
}
