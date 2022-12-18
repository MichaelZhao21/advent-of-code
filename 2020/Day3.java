package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day3 {

    final static int n = 323;
    final static int m = 31;
    static char[][] lines = new char[n][m];

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day3.in"));

        for (int i = 0; i < n; i++) {
            lines[i] = f.readLine().toCharArray();
        }
        f.close();

        // Part 2
        int[][] increments = {{1,1},{3,1},{5,1},{7,1},{1,2}};

        long product = 1;
        for (int[] in : increments) {
            product *= countTrees(in[0], in[1]);
        }

        System.out.println(product);
    }

    // Part 1
    public static int countTrees(int dx, int dy) {
        int x = 0;
        int y = 0;
        int trees = 0;
        while (y < n) {
            if (lines[y][x] == '#') trees++;
            x += dx;
            if (x >= m) x = x - m;
            y += dy;
        }
        return trees;
    }
}
