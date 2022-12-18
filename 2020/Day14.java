package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day14.in"));
        Pattern memPattern = Pattern.compile("mem\\[(.*?)\\] = (\\d+)");
        final int n = 551; // 551

        // Part 1
//        long[] mem = new long[100000];
//        char[] mask = new char[36];
//        for (int i = 0; i < n; i++) {
//            String line = f.readLine();
//            if (line.startsWith("mask")) {
//                mask = line.substring(7).toCharArray();
//            }
//            else {
//                Matcher m = memPattern.matcher(line);
//                if (m.find()) {
//                    mem[Integer.parseInt(m.group(1))] = calc(mask, m.group(2));
//                }
//                else System.out.println("uh oh");
//            }
//        }
//        long count = 0;
//        for (long m : mem) count += m;
//        System.out.println(count);

        // Part 2
        HashMap<Long, Long> mem = new HashMap<>();
        char[] mask = new char[36];
        for (int i = 0; i < n; i++) {
            String line = f.readLine();
            if (line.startsWith("mask")) {
                mask = line.substring(7).toCharArray();
            }
            else {
                Matcher m = memPattern.matcher(line);
                if (m.find()) {
                    ArrayList<Long> in = getIndex(mask, m.group(1));
                    long num = Long.parseLong(m.group(2));
                    for (Long inp : in) {
                        mem.put(inp, num);
                    }
                }
                else System.out.println("uh oh");
            }
        }
        long total = 0;
        for (Long l : mem.values()) total += l;
        System.out.println(total);
    }

    public static long calc(char[] mask, String in) {
        char[] cin = String.format("%36s", Integer.toBinaryString(Integer.parseInt(in))).replaceAll(" ", "0").toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 36; i++) {
            if (mask[i] == 'X') sb.append(cin[i]);
            else sb.append(mask[i]);
        }
        return new BigInteger(String.valueOf(sb), 2).longValue();
    }

    private static ArrayList<Long> getIndex(char[] mask, String in) {
        ArrayList<StringBuilder> perms = new ArrayList<>();
        char[] inc = String.format("%36s", Integer.toBinaryString(Integer.parseInt(in))).replaceAll(" ", "0").toCharArray();
        for (int i = 0; i < mask.length; i++) {
            if (mask[i] != 'X') {
                String currNum = "1";
                if (mask[i] != '1') currNum = Character.toString(inc[i]);
                if (perms.size() == 0) perms.add(new StringBuilder(currNum));
                else {
                    for (int j = 0; j < perms.size(); j++) {
                        perms.get(j).append(currNum);
                    }
                }
            }
            else {
                int prevSize = perms.size();
                for (int j = 0; j < prevSize; j++) {
                    perms.add(new StringBuilder(perms.get(j)).append("1"));
                    perms.get(j).append("0");
                }
            }
        }
        ArrayList<Long> out = new ArrayList<>();
        for (StringBuilder sb : perms)
            out.add(new BigInteger(sb.toString(), 2).longValue());
        return out;
    }
}
