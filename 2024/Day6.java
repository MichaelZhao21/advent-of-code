import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Day6 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day6.in"));

        // . = 0, # = 1, ^ = 2
        int n = 130;
        int[][] grid = new int[n][n];
        int sdir = 0, sx = 0, sy = 0; // up 0, right 1, down 2, left 3
        for (int i = 0; i < n; i++) {
            String[] ss = f.readLine().split("");
            for (int j = 0; j < n; j++) {
                if (ss[j].equals(".")) {
                    grid[i][j] = 0;
                } else if (ss[j].equals("#")) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = 0;
                    if (ss[j].equals(">")) sdir = 1; 
                    else if (ss[j].equals("v")) sdir = 2; 
                    else if (ss[j].equals("<")) sdir = 3; 
                    sy = i;
                    sx = j;
                }
            }
        }

        f.close();

        // Part 1
        int x = sx, y = sy, dir = sdir;
        boolean[][] filled = new boolean[n][n];
        HashSet<Pair> path = new HashSet<>(); // for part 2
        while (x > -1 && x < n && y > -1 && y < n) {
            filled[y][x] = true;
            path.add(new Pair(x, y)); // for part 2

            if (dir == 0 && y > 0 && grid[y-1][x] == 1) dir = 1;
            else if (dir == 1 && x < n-1 && grid[y][x+1] == 1) dir = 2;
            else if (dir == 2 && y < n-1 && grid[y+1][x] == 1) dir = 3;
            else if (dir == 3 && x > 0 && grid[y][x-1] == 1) dir = 0;
            else {
                if (dir == 0) y--;
                else if (dir == 1) x++;
                else if (dir == 2) y++;
                else x--;
            }
        }
        int count = 0;
        for (boolean[] bs : filled) {
            for (boolean b : bs) {
                if (b) count++;
            }
        }
        System.out.println(count);

        // Part 2
        int loops = 0;
        for (Pair p : path) {
            if (p.y == sy && p.x == sx) continue;
            grid[p.y][p.x] = 1;
            x = sx;
            y = sy;
            dir = sdir;
            HashSet<String> v = new HashSet<>();
            while (x > -1 && x < n && y > -1 && y < n) {
                filled[y][x] = true;
                String s = y + "," + x + "," + dir;
                if (v.contains(s)) {
                    loops++;
                    break;
                }
                v.add(s);

                if (dir == 0 && y > 0 && grid[y-1][x] == 1) dir = 1;
                else if (dir == 1 && x < n-1 && grid[y][x+1] == 1) dir = 2;
                else if (dir == 2 && y < n-1 && grid[y+1][x] == 1) dir = 3;
                else if (dir == 3 && x > 0 && grid[y][x-1] == 1) dir = 0;
                else {
                    if (dir == 0) y--;
                    else if (dir == 1) x++;
                    else if (dir == 2) y++;
                    else x--;
                }
            }
            grid[p.y][p.x] = 0;
        }
        System.out.println(loops);
    }

    public static class Pair {
        public int x;
        public int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}
