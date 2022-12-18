package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day17b {
    static int[][][][] cube;
    static final int n = 8; // 8
    static final int offset = 7;
    static final int s = offset * 2 + n;
    static final int z = offset * 2 + 1;
    static final int w = offset * 2 + 1;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day17.in"));
        cube = new int[w][z][s][s];
        for (int i = offset; i < offset + n; i++) {
            String line = f.readLine();
            for (int j = offset; j < offset + n; j++) {
                if (line.charAt(j - offset) == '#') cube[offset][offset][i][j] = 1;
            }
        }

//        for (int[][] cub : cube) {
//            for (int [] cu : cub) {
//                for (int c : cu) {
//                    System.out.print(c);
//                }
//                System.out.println();
//            }
//            System.out.println("--------------------");
//        }

        for (int c = 0; c < 6; c++) {
            System.out.println("Cycle: " + (c + 1));
            int[][][][] newCube = new int[w][z][s][s];
            for (int h = 1; h < w - 1; h++) {
                for (int i = 1; i < z - 1; i++) {
                    for (int j = 1; j < s - 1; j++) {
                        for (int k = 1; k < s - 1; k++) {
                            Count co = countCubes(h, i, j, k);
                            if (cube[h][i][j][k] == 1 && !(co.on == 2 || co.on == 3)) newCube[h][i][j][k] = 0;
                            else if (cube[h][i][j][k] == 0 && co.on == 3) newCube[h][i][j][k] = 1;
                            else newCube[h][i][j][k] = cube[h][i][j][k];
                        }
                    }
                }
            }
            cube = newCube;
        }

        int active = 0;
        for (int h = 1; h < w - 1; h++) {
            for (int i = 1; i < z - 1; i++) {
                for (int j = 1; j < s - 1; j++) {
                    for (int k = 1; k < s - 1; k++) {
                        if (cube[h][i][j][k] == 1) active++;
                    }
                }
            }
        }
        System.out.println(active);
    }
    public static class Count {
        int on, off;
        public Count(int on, int off) {
            this.on = on;
            this.off = off;
        }
    }
    public static Count countCubes(int w, int z, int y, int x) {
        int on = 0;
        int off = 0;
        if (cube[w - 1][z - 1][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z - 1][y - 1][x] == 0) off++; else on++;
        if (cube[w - 1][z - 1][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w - 1][z - 1][y][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z - 1][y][x] == 0) off++; else on++;
        if (cube[w - 1][z - 1][y][x + 1] == 0) off++; else on++;
        if (cube[w - 1][z - 1][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z - 1][y + 1][x] == 0) off++; else on++;
        if (cube[w - 1][z - 1][y + 1][x + 1] == 0) off++; else on++;
        if (cube[w - 1][z][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z][y - 1][x] == 0) off++; else on++;
        if (cube[w - 1][z][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w - 1][z][y][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z][y][x] == 0) off++; else on++;
        if (cube[w - 1][z][y][x + 1] == 0) off++; else on++;
        if (cube[w - 1][z][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z][y + 1][x] == 0) off++; else on++;
        if (cube[w - 1][z][y + 1][x + 1] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y - 1][x] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y][x] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y][x + 1] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y + 1][x] == 0) off++; else on++;
        if (cube[w - 1][z + 1][y + 1][x + 1] == 0) off++; else on++;
        if (cube[w][z - 1][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w][z - 1][y - 1][x] == 0) off++; else on++;
        if (cube[w][z - 1][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w][z - 1][y][x - 1] == 0) off++; else on++;
        if (cube[w][z - 1][y][x] == 0) off++; else on++;
        if (cube[w][z - 1][y][x + 1] == 0) off++; else on++;
        if (cube[w][z - 1][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w][z - 1][y + 1][x] == 0) off++; else on++;
        if (cube[w][z - 1][y + 1][x + 1] == 0) off++; else on++;
        if (cube[w][z][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w][z][y - 1][x] == 0) off++; else on++;
        if (cube[w][z][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w][z][y][x - 1] == 0) off++; else on++;
//        if (cube[w][z][y][x] == 0) off++; else on++;
        if (cube[w][z][y][x + 1] == 0) off++; else on++;
        if (cube[w][z][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w][z][y + 1][x] == 0) off++; else on++;
        if (cube[w][z][y + 1][x + 1] == 0) off++; else on++;
        if (cube[w][z + 1][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w][z + 1][y - 1][x] == 0) off++; else on++;
        if (cube[w][z + 1][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w][z + 1][y][x - 1] == 0) off++; else on++;
        if (cube[w][z + 1][y][x] == 0) off++; else on++;
        if (cube[w][z + 1][y][x + 1] == 0) off++; else on++;
        if (cube[w][z + 1][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w][z + 1][y + 1][x] == 0) off++; else on++;
        if (cube[w][z + 1][y + 1][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y - 1][x] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y][x] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y + 1][x] == 0) off++; else on++;
        if (cube[w + 1][z - 1][y + 1][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z][y - 1][x] == 0) off++; else on++;
        if (cube[w + 1][z][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z][y][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z][y][x] == 0) off++; else on++;
        if (cube[w + 1][z][y][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z][y + 1][x] == 0) off++; else on++;
        if (cube[w + 1][z][y + 1][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y - 1][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y - 1][x] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y - 1][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y][x] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y][x + 1] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y + 1][x - 1] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y + 1][x] == 0) off++; else on++;
        if (cube[w + 1][z + 1][y + 1][x + 1] == 0) off++; else on++;
        return new Count(on, off);
    }
}
