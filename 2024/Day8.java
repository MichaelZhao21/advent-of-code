import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day8 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day8.in"));

        int n = 12;
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            String[] ss = f.readLine().split("");
            for (int j = 0; j < n; j++) {
                grid[i][j] = ss[j].charAt(0);
            }
        }

        f.close();

        // Part 1
        boolean[][] visited = new boolean[n][n];
        HashMap<Character, ArrayList<Pair>> freqs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];

                if (c == '.')
                    continue;

                ArrayList<Pair> pl;
                if (freqs.containsKey(c)) {
                    pl = freqs.get(c);
                } else {
                    pl = new ArrayList<>();
                    freqs.put(c, pl);
                }
                pl.add(new Pair(i, j));
            }
        }

        for (Map.Entry<Character, ArrayList<Pair>> e : freqs.entrySet()) {
            ArrayList<Pair> fl = new ArrayList<>();
            for (int i = 0; i < fl.size(); i++) {
                for (int j = i; j < fl.size(); j++) {
                    // NEED TO FIGURE OUT HOW TO DETERMINE WHICH ONE IS ON "TOP"
                }
            }
        }
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
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}
