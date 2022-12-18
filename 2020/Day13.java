package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day13.in"));
        int time = Integer.parseInt(f.readLine());
        String[] line = f.readLine().split(",");
        f.close();

        // Part 1
        ArrayList<Integer> ids = new ArrayList<>();
        for (String s : line) {
            if (!s.equals("x")) ids.add(Integer.parseInt(s));
        }
        int minTime = Integer.MAX_VALUE;
        int minId = 0;
        for (int i : ids) {
            int diff = ((time / i + 1) * i) - time;
            if (minTime > diff) {
                minId = i;
                minTime = diff;
            }
        }
        System.out.println(minId * minTime);

        // Part 2: Plug this into Wolfram Alpha LMAO
        for (int i = 0; i < line.length; i++) {
            if (!line[i].equals("x")) System.out.printf("(t + %d) mod %s = 0, ", i, line[i]);
        }
    }
}
