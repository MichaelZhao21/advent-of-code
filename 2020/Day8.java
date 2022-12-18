package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day8 {
    final static int n = 642;
    static int[] op;
    static int[] num;
    static int acc = 0;
    static int tmpOp = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day8.in"));
        op = new int[n];
        num = new int[n];
        for (int i = 0; i < n; i++) {
            String[] line = f.readLine().split(" ");
            switch (line[0]) {
                case "acc":
                    op[i] = 0;
                    break;
                case "jmp":
                    op[i] = 1;
                    break;
                case "nop":
                    op[i] = 2;
                    break;
                default:
                    System.out.println("uh oh");
                    System.exit(1);
            }
            num[i] = Integer.parseInt(line[1]);
        }
        f.close();

        for (int i = 0; i < n; i++) {
            if (op[i] != 0) {
                tmpOp = op[i];
                op[i] = (tmpOp == 2 ? 1 : 2);
                if (run()) {
                    System.out.println("good");
                    break;
                }
                op[i] = tmpOp;
            }
        }

        System.out.println(acc);
    }

    public static boolean run() {
        acc = 0;
        int counter = 0;
        boolean[] exec = new boolean[n];
        while (counter < n && !exec[counter]) {
            exec[counter] = true;
            if (op[counter] == 0) {
                acc += num[counter];
                counter++;
            }
            else if (op[counter] == 1) {
                counter += num[counter];
            }
            else {
                counter++;
            }
        }
        return counter >= n;
    }
}
