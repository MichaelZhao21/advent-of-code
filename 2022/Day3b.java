import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day3b {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day3.in"));

        String line;
        Set<Character> set = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        int sum = 0;
        while ((line = f.readLine()) != null) {
            for (char c : line.toCharArray()) {
                set.add(c);
            }
            String second = f.readLine();
            for (char c : second.toCharArray()) {
                if (set.contains(c)) {
                    set2.add(c);
                }
            }
            String third = f.readLine();
            for (char c : third.toCharArray()) {
                if (set2.contains(c)) {
                    if (c < 97) {
                        sum += c - 38;
                    } else {
                        sum += c - 96;
                    }
                    break;
                }
            }
            set.clear();
            set2.clear();
        }

        System.out.println(sum);

        f.close();
    }
}
