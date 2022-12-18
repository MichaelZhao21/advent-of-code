import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day3.in"));

        String line;
        Set<Character> set = new HashSet<>();
        int sum = 0;
        while ((line = f.readLine()) != null) {
            String first = line.substring(0, line.length() / 2);
            String second = line.substring(line.length() / 2);
            for (char c : first.toCharArray()) {
                set.add(c);
            }
            for (char c : second.toCharArray()) {
                if (set.contains(c)) {
                    if (c < 97) {
                        sum += c - 38;
                    } else {
                        sum += c - 96;
                    }
                    break;
                }
            }
            set.clear();
        }

        System.out.println(sum);

        f.close();
    }
}
