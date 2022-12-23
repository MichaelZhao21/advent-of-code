import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Day10 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day10.in"));

        LinkedList<Integer> keyCycles = new LinkedList<>(Arrays.asList(20, 60, 100, 140, 180, 220));

        String line;
        int cycle = 0;
        int x = 1;
        long sum = 0;
        while ((line = f.readLine()) != null) {
            String[] ls = line.split(" ");
            if (ls[0].equals("addx")) {
                cycle += 2;
            } else {
                cycle += 1;
            }

            if (keyCycles.isEmpty()) break;

            if (cycle >= keyCycles.peek()) {
                int key = keyCycles.pop();
                sum += key * x;
            }

            if (ls[0].equals("addx")) {
                x += Integer.parseInt(ls[1]);
            }
        }

        System.out.println(sum);

        f.close();
    }
}
