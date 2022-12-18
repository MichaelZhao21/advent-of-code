import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2b {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day2.in"));

        String line;
        int score = 0;
        while ((line = f.readLine()) != null) {
            char a = line.charAt(0);
            char b = line.charAt(2);

            if (a == 'A' && b == 'X') {
                score += 3;
            } else if (a == 'A' && b == 'Y') {
                score += 4;
            } else if (a == 'A' && b == 'Z') {
                score += 8;
            } else if (a == 'B' && b == 'X') {
                score += 1;
            } else if (a == 'B' && b == 'Y') {
                score += 5;
            } else if (a == 'B' && b == 'Z') {
                score += 9;
            } else if (a == 'C' && b == 'X') {
                score += 2;
            } else if (a == 'C' && b == 'Y') {
                score += 6;
            } else if (a == 'C' && b == 'Z') {
                score += 7;
            }
        }

        System.out.println(score);

        f.close();
    }
}
