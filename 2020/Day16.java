package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day16 {
    static Field[] fields;
    final static int t = 20; // 20
    final static int n = 238; // 238
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day16.in"));
        fields = new Field[t];
        int[][] tickets = new int[n][t];
        for (int i = 0; i < t; i++) {
            String in = f.readLine().split(": ")[1];
            String[] in2 = in.split(" or ");
            String[] in2a = in2[0].split("-");
            String[] in2b = in2[1].split("-");
            fields[i] = new Field(Integer.parseInt(in2a[0]), Integer.parseInt(in2a[1]), Integer.parseInt(in2b[0]), Integer.parseInt(in2b[1]));
        }
        f.readLine();
        f.readLine();
        int[] myTicket = Arrays.stream(f.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        f.readLine();
        f.readLine();
        for (int i = 0; i < n; i++) {
            tickets[i] = Arrays.stream(f.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }

        // Part 1
        int error = 0;
        boolean good;
        ArrayList<int[]> goodTickets = new ArrayList<>();
        for (int[] tick : tickets) {
            good = true;
            for (int ti : tick) {
                if (!isValid(ti)) {
                    error += ti;
                    good = false;
                }
            }
            if (good) goodTickets.add(tick);
        }
        System.out.println(error);

        // Part 2
        goodTickets.add(myTicket);
        int[][] tickets2 = new int[goodTickets.size()][t];
        for (int i = 0; i < goodTickets.size(); i++) {
            tickets2[i] = goodTickets.get(i);
        }
        int[][] board = new int[t][t];
        for (int i = 0; i < tickets2.length; i++) {
            for (int j = 0; j < t; j++) {
                for (int k = 0; k < t; k++) {
                    if (!isOneValid(tickets2[i][j], fields[k])) board[j][k] = -1;
                }
            }
        }
        boolean changes = true;
        while (changes) {
            changes = false;
            for (int i = 0; i < t; i++) {
                int colCount = 0;
                int colIndex = 0;
                int rowCount = 0;
                int rowIndex = 0;
                for (int j = 0; j < t; j++) {
                    if (board[i][j] == 0) {
                        rowCount++;
                        rowIndex = j;
                    }
                    if (board[j][i] == 0) {
                        colCount++;
                        colIndex = j;
                    }
                }
                if (rowCount == 1) {
                    changes = true;
                    board[i][rowIndex] = 1;
                    for (int j = 0; j < t; j++) {
                        if (board[j][rowIndex] == 0) board[j][rowIndex] = -1;
                    }
                }
                if (colCount == 1) {
                    changes = true;
                    board[colIndex][i] = 1;
                    for (int j = 0; j < t; j++) {
                        if (board[colIndex][j] == 0) board[colIndex][j] = -1;
                    }
                }
            }
        }
        int[] poss = new int[t];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                if (board[j][i] == 1) {
                    poss[i] = j;
                    break;
                }
            }
        }
        long product = 1;
        for (int i = 0; i < 6; i++) {
            product *= myTicket[poss[i]];
        }
        System.out.print("|");
        for (int[] bo : board) {
            for (int b : bo) {
                if (b == -1) System.out.print(".");
                else if (b == 0) System.out.print(" ");
                else if (b == 1) System.out.print("O");
            }
            System.out.print("|\n|");
        }
        System.out.println();
        System.out.println(Arrays.toString(poss));
        System.out.println(product);
    }
    public static class Field {
        int a, b, c, d;
        public Field(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
    public static boolean isValid(int num) {
        for (Field f : fields) {
            if ((f.a <= num && f.b >= num) || (f.c <= num && f.d >= num)) return true;
        }
        return false;
    }
    public static boolean isOneValid(int num, Field f) {
        return (f.a <= num && f.b >= num) || (f.c <= num && f.d >= num);
    }
}
