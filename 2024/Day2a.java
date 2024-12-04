import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2a {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day2.in"));

        int n = 1000;
        ArrayList<Integer>[] levels = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            String s = f.readLine();
            String[] ss = s.split(" ");
            levels[i] = new ArrayList<>();
            for (String a : ss) {
                levels[i].add(Integer.parseInt(a));
            }
        }

        f.close();

        // Part 1
        int safe = 0;
        boolean check = false;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> c = levels[i];
            check = c.get(0) - c.get(1) > 0;
            boolean safen = true;
            for (int j = 1; j < c.size(); j++) {
                if ((c.get(j-1) - c.get(j) > 0) != check) {
                    // System.out.println(c.get(j-1) + " " + c.get(j) + " " + i);
                    safen = false;
                    break;
                }
                int diff = Math.abs(c.get(j-1) - c.get(j));
                if (diff < 1 || diff > 3) {
                    // System.out.println("D " + c.get(j-1) + " " + c.get(j) + " " + i);
                    safen = false;
                    break;
                }
            }
            if (safen) safe++;
        }

        System.out.println(safe);
    }
}
