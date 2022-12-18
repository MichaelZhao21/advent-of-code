package y_2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day7 {
    public static int[] inNums;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2019/day7.in"));
        inNums = Arrays.stream(f.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        f.close();
        String[] phases = { "01234", "01243", "01324", "01342", "01423", "01432", "02134", "02143", "02314", "02341", "02413", "02431", "03124", "03142", "03214", "03241", "03412", "03421", "04123", "04132", "04213", "04231", "04312", "04321", "10234", "10243", "10324", "10342", "10423", "10432", "12034", "12043", "12304", "12340", "12403", "12430", "13024", "13042", "13204", "13240", "13402", "13420", "14023", "14032", "14203", "14230", "14302", "14320", "20134", "20143", "20314", "20341", "20413", "20431", "21034", "21043", "21304", "21340", "21403", "21430", "23014", "23041", "23104", "23140", "23401", "23410", "24013", "24031", "24103", "24130", "24301", "24310", "30124", "30142", "30214", "30241", "30412", "30421", "31024", "31042", "31204", "31240", "31402", "31420", "32014", "32041", "32104", "32140", "32401", "32410", "34012", "34021", "34102", "34120", "34201", "34210", "40123", "40132", "40213", "40231", "40312", "40321", "41023", "41032", "41203", "41230", "41302", "41320", "42013", "42031", "42103", "42130", "42301", "42310", "43012", "43021", "43102", "43120", "43201", "43210" };
        int max = 0;
        for (String phaseSet : phases) {
            int in = 0;
            for (char c : phaseSet.toCharArray()) {
                in = run(in, c - 48);
            }
            if (max < in)
                max = in;
        }
        System.out.println(max);
    }

    public static int run(int input, int phase) {
        int index = 0;
        int increment = 4;
        int[] nums = Arrays.copyOf(inNums, inNums.length);
        boolean scanned = false;
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
                int n1 = scanned ? input : phase;
                scanned = true;
                nums[nums[index + 1]] = n1;
                increment = 2;
            }
            else if (opCode == 4) {
                return modes[0] == '0' ? nums[nums[index + 1]] : nums[index + 1];
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
        System.out.println("uh oh");
        return 0;
    }
}
