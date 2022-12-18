package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day6 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day6.in"));
        final int n = 2191;
        ArrayList<String> inputs = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int see = 0;
        for (int i = 0; i < n; i++) {
            String line = f.readLine();
            if (line.isEmpty()) {
                inputs.add(sb.toString());
                counts.add(see);
                sb = new StringBuilder();
                see = 0;
            }
            else {
                sb.append(line);
                see++;
            }
        }

        // Part 1
        int total = 0;
        for (String s : inputs) {
            HashSet<Character> counted = new HashSet<>();
            int currCount = 0;
            for (char c : s.toCharArray()) {
                if (!counted.contains(c)) {
                    counted.add(c);
                    currCount++;
                }
            }
            total += currCount;
        }
        System.out.println(total);

        // Part 2
        total = 0;
        for (int i = 0; i < inputs.size(); i++) {
            String s = inputs.get(i);
            int count = counts.get(i);
            HashMap<Character, Integer> currTotals = new HashMap<>();
            for (char c : s.toCharArray()) {
                if (!currTotals.containsKey(c)) currTotals.put(c, 1);
                else currTotals.put(c, currTotals.get(c) + 1);
            }
            for (int a : currTotals.values())
                if (a == count) total++;
        }
        System.out.println(total);
    }
}
