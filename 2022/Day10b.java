import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day10b {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day10.in"));

        String line;
        int cycle = 0;
        int x = 1;
        while ((line = f.readLine()) != null) {
            String[] ls = line.split(" ");

            if (cycle % 40 == 0) System.out.println();
            if (Math.abs((cycle % 40) - x) <= 1) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }

            if (ls[0].equals("addx")) {
                cycle++;
                if (cycle % 40 == 0) System.out.println();
                if (Math.abs((cycle % 40) - x) <= 1) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
                cycle++;
            } else {
                cycle++;
            }

            if (ls[0].equals("addx")) {
                x += Integer.parseInt(ls[1]);
            }
        }
        System.out.println();

        f.close();
    }
}
