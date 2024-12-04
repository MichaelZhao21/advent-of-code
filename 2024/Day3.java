import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day3.in"));
        int n = 6;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(f.readLine());
        }
        String s = sb.toString();

        f.close();

        // Part 1
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        Matcher m = Pattern.compile("mul\\((\\d+),(\\d+)\\)").matcher(s);
        while (m.find()) {
            a.add(Integer.parseInt(m.group(1)));
            b.add(Integer.parseInt(m.group(2)));
        }
        long total = 0;
        for (int i = 0; i < a.size(); i++) {
            total += a.get(i) * b.get(i);
        }
        System.out.println(total);

        // Part 2
        a.clear();
        b.clear();
        m = Pattern.compile("(?:(mul)\\((\\d+),(\\d+)\\))|(?:(do)\\(\\))|(?:(don't)\\(\\))").matcher(s);
        boolean active = true;
        while (m.find()) {
            if (m.group(1) != null) {
                if (!active) continue;
                a.add(Integer.parseInt(m.group(2)));
                b.add(Integer.parseInt(m.group(3)));
            } else {
                active = m.group(4) != null;
            }
        }
        total = 0;
        for (int i = 0; i < a.size(); i++) {
            total += a.get(i) * b.get(i);
        }
        System.out.println(total);
    }
}
