package y_2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2019/day3.in"));
        ArrayList<Wire> line1 = new ArrayList<>();
        ArrayList<Wire> line2 = new ArrayList<>();
        ArrayList<Cross> crosses = new ArrayList<>();

        String[] in1 = f.readLine().split(",");
        String[] in2 = f.readLine().split(",");
        f.close();

        long currX = 0, currY = 0, steps = 0;
        boolean vert;
        long dist, min, max, pos;
        for (String s : in1) {
            if (s.startsWith("R")) {
                vert = false;
                dist = Integer.parseInt(s.substring(1));
            }
            else if (s.startsWith("L")) {
                vert = false;
                dist = -Integer.parseInt(s.substring(1));
            }
            else if (s.startsWith("U")) {
                vert = true;
                dist = Integer.parseInt(s.substring(1));
            }
            else {
                vert = true;
                dist = -Integer.parseInt(s.substring(1));
            }
            if (vert) {
                if (dist < 0) {
                    min = currY + dist;
                    max = currY;
                }
                else {
                    max = currY + dist;
                    min = currY;
                }
                pos = currX;
                currY += dist;
            }
            else {
                if (dist < 0) {
                    min = currX + dist;
                    max = currX;
                }
                else {
                    max = currX + dist;
                    min = currX;
                }
                pos = currY;
                currX += dist;
            }
            line1.add(new Wire(min, max, pos, steps, vert, dist > 0));
            steps += Math.abs(dist);
        }

        currX = 0;
        currY = 0;
        steps = 0;
        for (String s : in2) {
            if (s.startsWith("R")) {
                vert = false;
                dist = Integer.parseInt(s.substring(1));
            }
            else if (s.startsWith("L")) {
                vert = false;
                dist = -Integer.parseInt(s.substring(1));
            }
            else if (s.startsWith("U")) {
                vert = true;
                dist = Integer.parseInt(s.substring(1));
            }
            else {
                vert = true;
                dist = -Integer.parseInt(s.substring(1));
            }
            if (vert) {
                if (dist < 0) {
                    min = currY + dist;
                    max = currY;
                }
                else {
                    max = currY + dist;
                    min = currY;
                }
                pos = currX;
                currY += dist;
            }
            else {
                if (dist < 0) {
                    min = currX + dist;
                    max = currX;
                }
                else {
                    max = currX + dist;
                    min = currX;
                }
                pos = currY;
                currX += dist;
            }
            line2.add(new Wire(min, max, pos, steps, vert, dist > 0));
            steps += Math.abs(dist);
        }

        long ws1, ws2;
        for (Wire w1 : line1) {
            for (Wire w2 : line2) {
                if (w1.vert != w2.vert && w1.pos >= w2.min && w1.pos <= w2.max && w2.pos >= w1.min && w2.pos <= w1.max) {
                    ws1 = Math.abs(w2.pos - (w1.headMin ? w1.min : w1.max)) + w1.steps;
                    ws2 = Math.abs(w1.pos - (w2.headMin ? w2.min : w2.max)) + w2.steps;
                    if (w1.vert) crosses.add(new Cross(w1.pos, w2.pos, ws1, ws2));
                    else crosses.add(new Cross(w2.pos, w1.pos, ws1, ws2));
                }
            }
        }

        // Part 1
        long minDist = Long.MAX_VALUE;
        for (Cross c : crosses) {
            long currDist = Math.abs(c.x) + Math.abs(c.y);
            if (minDist > currDist && !(c.x == 0 && c.y == 0)) {
                minDist = currDist;
            }
        }
        System.out.println(minDist);

        // Part 2
        long minSteps = Long.MAX_VALUE;
        for (Cross c : crosses) {
            long currSteps = c.steps1 + c.steps2;
            if (minSteps > currSteps && !(c.x == 0 && c.y == 0)) {
                minSteps = currSteps;
            }
        }
        System.out.println(minSteps);
    }

    public static class Wire {
        long min, max, pos, steps;
        boolean vert, headMin;
        public Wire(long min, long max, long pos, long steps, boolean vert, boolean headMin) {
            this.min = min;
            this.max = max;
            this.pos = pos;
            this.vert = vert;
            this.steps = steps;
            this.headMin = headMin;
        }
    }

    public static class Cross {
        long x;
        long y;
        long steps1;
        long steps2;
        public Cross(long x, long y, long steps1, long steps2) {
            this.x = x;
            this.y = y;
            this.steps1 = steps1;
            this.steps2 = steps2;
        }
    }
}
