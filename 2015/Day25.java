package y_2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day25 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2015/day25.in"));
        long num = Long.parseLong(f.readLine());
        int row = Integer.parseInt(f.readLine());
        int col = Integer.parseInt(f.readLine());
        f.close();

        long startRow = row + col - 1;
        long calcs = (startRow - 1) * startRow / 2 - 1 + col;

        for (int i = 0; i < calcs; i++) {
            num *= 252533;
            num %= 33554393;
        }

        System.out.println(num);
    }
}
