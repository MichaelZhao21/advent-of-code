import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class Day10 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day10.in"));

        int n = 60;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = f.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = s.charAt(j) - '0';
            }
        }

        f.close();

        // Part 1
        HashSet<Integer>[][] dp = new HashSet[n][n];
        boolean[][] visited = new boolean[n][n];
        LinkedList<Pair> q = new LinkedList<>();
        int pk = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
                if (grid[i][j] == 9) {
                    q.add(new Pair(i, j));
                    dp[i][j].add(pk++);
                }
            }
        }

        while (!q.isEmpty()) {
            Pair c = q.remove();
            int cn = grid[c.x][c.y];
            
            if (c.x>0 && grid[c.x-1][c.y] == cn-1) {
                dp[c.x-1][c.y].addAll(dp[c.x][c.y]);
                if (!visited[c.x-1][c.y]) {
                    q.add(new Pair(c.x-1, c.y));
                    visited[c.x-1][c.y] = true;
                }
            }
            if (c.x<n-1 && grid[c.x+1][c.y] == cn-1) {
                dp[c.x+1][c.y].addAll(dp[c.x][c.y]);
                if (!visited[c.x+1][c.y]) {
                    q.add(new Pair(c.x+1, c.y));
                    visited[c.x+1][c.y] = true;
                }
            }
            if (c.y>0 && grid[c.x][c.y-1] == cn-1) {
                dp[c.x][c.y-1].addAll(dp[c.x][c.y]);
                if (!visited[c.x][c.y-1]) {
                    q.add(new Pair(c.x, c.y-1));
                    visited[c.x][c.y-1] = true;
                }
            }
            if (c.y<n-1 && grid[c.x][c.y+1] == cn-1) {
                dp[c.x][c.y+1].addAll(dp[c.x][c.y]);
                if (!visited[c.x][c.y+1]) {
                    q.add(new Pair(c.x, c.y+1));
                    visited[c.x][c.y+1] = true;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    sum += dp[i][j].size();
                }
            }
        }
        System.out.println(sum);

        // Part 2
        int[][] dp2 = new int[n][n];
        visited = new boolean[n][n];
        q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 9) {
                    q.add(new Pair(i, j));
                    dp2[i][j] = 1;
                }
            }
        }

        while (!q.isEmpty()) {
            Pair c = q.remove();
            int cn = grid[c.x][c.y];
            
            if (c.x>0 && grid[c.x-1][c.y] == cn-1) {
                dp2[c.x-1][c.y] += dp2[c.x][c.y];
                if (!visited[c.x-1][c.y]) {
                    q.add(new Pair(c.x-1, c.y));
                    visited[c.x-1][c.y] = true;
                }
            }
            if (c.x<n-1 && grid[c.x+1][c.y] == cn-1) {
                dp2[c.x+1][c.y] += dp2[c.x][c.y];
                if (!visited[c.x+1][c.y]) {
                    q.add(new Pair(c.x+1, c.y));
                    visited[c.x+1][c.y] = true;
                }
            }
            if (c.y>0 && grid[c.x][c.y-1] == cn-1) {
                dp2[c.x][c.y-1] += dp2[c.x][c.y];
                if (!visited[c.x][c.y-1]) {
                    q.add(new Pair(c.x, c.y-1));
                    visited[c.x][c.y-1] = true;
                }
            }
            if (c.y<n-1 && grid[c.x][c.y+1] == cn-1) {
                dp2[c.x][c.y+1] += dp2[c.x][c.y];
                if (!visited[c.x][c.y+1]) {
                    q.add(new Pair(c.x, c.y+1));
                    visited[c.x][c.y+1] = true;
                }
            }
        }

        sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    sum += dp2[i][j];
                }
            }
        }
        System.out.println(sum);
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
