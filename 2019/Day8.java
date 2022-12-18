package y_2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day8 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2019/day8.in"));
        String s = f.readLine();
        f.close();

        // Part 1
        final int layerSize = 25 * 6;
        int layerCount = s.length() / layerSize;
        long minZero = Long.MAX_VALUE;
        long finalVal = 0;
        for (int i = 0; i < layerCount; i++) {
            long zero = 0;
            long one = 0;
            long two = 0;
            String curr = s.substring(i * layerSize, (i + 1) * layerSize);
            for (char c : curr.toCharArray()) {
                if (c == '0') zero++;
                else if (c == '1') one++;
                else if (c == '2') two++;
            }
            if (minZero > zero) {
                minZero = zero;
                finalVal = one * two;
            }
        }
        System.out.println(finalVal);

        // Part 2
        int[] finalImage = new int[layerSize];
        Arrays.fill(finalImage, 2);
        for (int i = 0; i < layerCount; i++) {
            String curr = s.substring(i * layerSize, (i + 1) * layerSize);
            char[] chars = curr.toCharArray();
            for (int j = 0; j < layerSize; j++) {
                if (finalImage[j] == 2 && chars[j] != '2') finalImage[j] = chars[j] - 48;
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 25; j++) {
                System.out.print(finalImage[i * 25 + j] == 1 ? "O" : " ");
            }
            System.out.println();
        }
    }
}
