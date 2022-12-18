package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day11 {
    final static int n = 98;
    static int[][] seats = new int[n][n];
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day11.in"));
        for (int i = 0; i < n; i++) {
            String line = f.readLine();
            char[] chars = line.toCharArray();
            for (int j = 0; j < n; j++) {
                if (chars[j] == '.') seats[i][j] = 0;
                else seats[i][j] = 1;
            }
        }
        f.close();

        // Part 1
//        int changes = 1;
//        while (changes != 0) {
//            changes = 0;
//            int[][] newSeats = new int[n][n];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (seats[i][j] == 0) newSeats[i][j] = 0;
//                    else if (seats[i][j] == 1 &&
//                            (i == 0 || seats[i - 1][j] != 2) &&
//                            (j == 0 || seats[i][j - 1] != 2) &&
//                            (i == n - 1 || seats[i + 1][j] != 2) &&
//                            (j == n - 1 || seats[i][j + 1] != 2) &&
//                            (i == 0 || j == 0 || seats[i - 1][j - 1] != 2) &&
//                            (i == 0 || j == n - 1 || seats[i - 1][j + 1] != 2) &&
//                            (i == n - 1 || j == n - 1 || seats[i + 1][j + 1] != 2) &&
//                            (i == n - 1 || j == 0 || seats[i + 1][j - 1] != 2)) {
//                        changes++;
//                        newSeats[i][j] = 2;
//                    }
//                    else if (seats[i][j] == 2) {
//                        int count = 0;
//                        if (i != 0 && seats[i - 1][j] == 2) count++;
//                        if (j != 0 && seats[i][j - 1] == 2) count++;
//                        if (i != n - 1 && seats[i + 1][j] == 2) count++;
//                        if (j != n - 1 && seats[i][j + 1] == 2) count++;
//                        if (!(i == 0 || j == 0) && seats[i - 1][j - 1] == 2) count++;
//                        if (!(i == 0 || j == n - 1) && seats[i - 1][j + 1] == 2) count++;
//                        if (!(i == n - 1 || j == n - 1) && seats[i + 1][j + 1] == 2) count++;
//                        if (!(i == n - 1 || j == 0) && seats[i + 1][j - 1] == 2) count++;
//                        if (count >= 4) {
//                            changes++;
//                            newSeats[i][j] = 1;
//                        }
//                        else newSeats[i][j] = 2;
//                    }
//                    else newSeats[i][j] = seats[i][j];
//                }
//            }
//            seats = newSeats;
//        }

        // Part 2
        int changes = 1;
        while (changes != 0) {
            changes = 0;
            int[][] newSeats = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (seats[i][j] == 0) newSeats[i][j] = 0;
                    else {
                        int[] dirs = check(i, j);
                        int k;
                        if (seats[i][j] == 1) {
                            for (k = 0; k < 8; k++) if (dirs[k] == 2) break;
                            if (k == 8) {
                                changes++;
                                newSeats[i][j] = 2;
                            }
                            else newSeats[i][j] = 1;
                        }
                        else {
                            int count = 0;
                            for (k = 0; k < 8; k++) if (dirs[k] == 2) count++;
                            if (count >= 5) {
                                changes++;
                                newSeats[i][j] = 1;
                            }
                            else newSeats[i][j] = 2;
                        }
                    }
                }
            }
            seats = newSeats;
        }

        int count = 0;
        for (int[] s : seats) {
            for (int i : s) {
                if (i == 2) count++;
            }
        }
        System.out.println(count);
    }
    public static int[] check(int y, int x) {
        int[] dirs = new int[8]; // 0 = Up then CW

        int currY;
        int currX = x;

        // Up
        for (currY = y - 1; currY >= 0; currY--) if (seats[currY][currX] != 0) break;
        if (currY != -1) dirs[0] = seats[currY][currX];
        else dirs[0] = 0;

        // Down
        for (currY = y + 1; currY < n; currY++) if (seats[currY][currX] != 0) break;
        if (currY != n) dirs[4] = seats[currY][currX];
        else dirs[4] = 0;

        currY = y;

        // Left
        for (currX = x - 1; currX >= 0; currX--) if (seats[currY][currX] != 0) break;
        if (currX != -1) dirs[6] = seats[currY][currX];
        else dirs[6] = 0;

        // Right
        for (currX = x + 1; currX < n; currX++) if (seats[currY][currX] != 0) break;
        if (currX != n) dirs[2] = seats[currY][currX];
        else dirs[2] = 0;

        // Up-Left
        for (currY = y - 1, currX = x - 1; currY >= 0 && currX >= 0; currY--, currX--) if (seats[currY][currX] != 0) break;
        if (currY != -1 && currX != -1) dirs[7] = seats[currY][currX];
        else dirs[7] = 0;

        // Up-Right
        for (currY = y - 1, currX = x + 1; currY >= 0 && currX < n; currY--, currX++) if (seats[currY][currX] != 0) break;
        if (currY != -1 && currX != n) dirs[1] = seats[currY][currX];
        else dirs[1] = 0;

        // Down-Left
        for (currY = y + 1, currX = x - 1; currY < n && currX >= 0; currY++, currX--) if (seats[currY][currX] != 0) break;
        if (currY != n && currX != -1) dirs[5] = seats[currY][currX];
        else dirs[5] = 0;

        // Down-Right
        for (currY = y + 1, currX = x + 1; currY < n && currX < n; currY++, currX++) if (seats[currY][currX] != 0) break;
        if (currY != n && currX != n) dirs[3] = seats[currY][currX];
        else dirs[3] = 0;

        return dirs;
    }
}
