package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day12 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day12.in"));
        final int n = 784;
        String[] dirs = new String[n];
        for (int i = 0; i < n; i++) {
            dirs[i] = f.readLine();
        }

        // Part 1
        char currDir;
        int angle = 0;
        int x = 0;
        int y = 0;
        for (String s : dirs) {
            if (angle < 0) angle = 360 + angle;
            if (angle % 360 == 0) currDir = 'E';
            else if (angle % 360 == 90) currDir = 'N';
            else if (angle % 360 == 180) currDir = 'W';
            else currDir = 'S';

            char start = s.charAt(0);
            if (start == 'F') start = currDir;

            int num = Integer.parseInt(s.substring(1));
            if (start == 'L') angle += num;
            else if (start == 'R') angle -= num;
            else if (start == 'N') y += num;
            else if (start == 'S') y -= num;
            else if (start == 'E') x += num;
            else x -= num;
        }
        System.out.println(Math.abs(x) + Math.abs(y));

        // Part 2
        int shipX = 0;
        int shipY = 0;
        int wayX = 10;
        int wayY = 1;
        for (String s : dirs) {
            int tmpWayX = wayX;
            int tmpWayY = wayY;
            if (s.equals("R90") || s.equals("L270")) {
                wayX = tmpWayY;
                wayY = -tmpWayX;
                continue;
            }
            else if (s.equals("R270") || s.equals("L90")) {
                wayX = -tmpWayY;
                wayY = tmpWayX;
                continue;
            }
            else if (s.equals("R180") || s.equals("L180")) {
                wayX = -wayX;
                wayY = -wayY;
                continue;
            }

            char dir = s.charAt(0);
            int num = Integer.parseInt(s.substring(1));

            if (dir == 'F') {
                shipX += num * wayX;
                shipY += num * wayY;
            }
            else if (dir == 'N') wayY += num;
            else if (dir == 'E') wayX += num;
            else if (dir == 'S') wayY -= num;
            else if (dir == 'W') wayX -= num;
            else System.out.println("uh oh");
        }
        System.out.println(Math.abs(shipX) + Math.abs(shipY));
    }
}
