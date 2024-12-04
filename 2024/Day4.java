import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day4.in"));

        int n = 140;
        char[][] g = new char[n][n];
        for (int i = 0; i < g.length; i++) {
            g[i] = f.readLine().toCharArray();
        }

        f.close();

        // Part 1
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != 'X') continue;

                if (i - 3 >= 0 && g[i-1][j] == 'M' && g[i-2][j] == 'A' && g[i-3][j] == 'S') count++;
                if (i + 4 <= n && g[i+1][j] == 'M' && g[i+2][j] == 'A' && g[i+3][j] == 'S') count++;
                if (j - 3 >= 0 && g[i][j-1] == 'M' && g[i][j-2] == 'A' && g[i][j-3] == 'S') count++;
                if (j + 4 <= n && g[i][j+1] == 'M' && g[i][j+2] == 'A' && g[i][j+3] == 'S') count++;
                
                if (i - 3 >= 0 && j - 3 >= 0 && g[i-1][j-1] == 'M' && g[i-2][j-2] == 'A' && g[i-3][j-3] == 'S') count++;
                if (i - 3 >= 0 && j + 4 <= n && g[i-1][j+1] == 'M' && g[i-2][j+2] == 'A' && g[i-3][j+3] == 'S') count++;
                if (i + 4 <= n && j - 3 >= 0 && g[i+1][j-1] == 'M' && g[i+2][j-2] == 'A' && g[i+3][j-3] == 'S') count++;
                if (i + 4 <= n && j + 4 <= n && g[i+1][j+1] == 'M' && g[i+2][j+2] == 'A' && g[i+3][j+3] == 'S') count++;
            }
        }
        System.out.println(count);

        // Part 2
        count = 0;
        for (int i = 1; i < n-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (g[i][j] != 'A') continue;

                if (g[i-1][j-1] == 'M' && g[i+1][j+1] == 'S' && g[i-1][j+1] == 'M' && g[i+1][j-1] == 'S') count++;
                if (g[i-1][j-1] == 'S' && g[i+1][j+1] == 'M' && g[i-1][j+1] == 'S' && g[i+1][j-1] == 'M') count++;
                if (g[i-1][j-1] == 'M' && g[i+1][j+1] == 'S' && g[i+1][j-1] == 'M' && g[i-1][j+1] == 'S') count++;
                if (g[i-1][j-1] == 'S' && g[i+1][j+1] == 'M' && g[i+1][j-1] == 'S' && g[i-1][j+1] == 'M') count++;
            }
        }
        System.out.println(count);
    }
}
