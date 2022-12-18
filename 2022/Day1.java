import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day1.in"));

        String line;
        int total = 0;
        int max = 0;
        while ((line = f.readLine()) != null) {
            if (line.equals("")) {
                if (total > max) {
                    max = total;
                }
                total = 0;
            } else {
                total += Integer.parseInt(line);
            }
        }
        if (total > max) {
            max = total;
        }

        System.out.println(max);

        f.close();
    }
}
