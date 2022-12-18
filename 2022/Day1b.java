import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1b {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day1.in"));

        String line;
        int total = 0;
        int max = 0;
        int two = 0;
        int three = 0;
        while ((line = f.readLine()) != null) {
            if (line.equals("")) {
                if (total >= max) {
                    three = two;
                    two = max;
                    max = total;
                } else if (total >= two) {
                    three = two;
                    two = total;
                } else if (total > three) {
                    three = max;
                }
                total = 0;
            } else {
                total += Integer.parseInt(line);
            }
        }
        if (total >= max) {
            three = two;
            two = max;
            max = total;
        } else if (total >= two) {
            three = two;
            two = total;
        } else if (total > three) {
            three = max;
        }

        System.out.println(max + two + three);

        f.close();
    }
}
