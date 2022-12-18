package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day4.in"));
        final int n = 990;
        ArrayList<String> inputs = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String line = f.readLine();
            if (line.isEmpty()) {
                inputs.add(sb.toString());
                sb = new StringBuilder();
            }
            else sb.append(" ").append(line);
        }
        f.close();

        Pattern numPattern = Pattern.compile("^\\d{4}$");
        Pattern heightPattern = Pattern.compile("^(\\d+)(cm|in)$");
        Pattern hclPattern = Pattern.compile("^#[0-9a-f]{6}$");
        Pattern eclPattern = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
        Pattern pidPattern = Pattern.compile("^\\d{9}$");
        int total = 0;
        for (String s : inputs) {
            String[] list = s.trim().split(" ");
            int valid = 0;

            // Part 1
//            for (String l : list) {
//                if (!l.split(":")[0].equals("cid")) valid++;
//            }

            label:
            for (String l : list) {
                String[] in = l.split(":");
                if (in[0].equals("cid")) continue;
                switch (in[0]) {
                    case "byr": {
                        Matcher m = numPattern.matcher(in[1]);
                        if (m.find()) {
                            int num = Integer.parseInt(in[1]);
                            if (num >= 1920 && num <= 2002) valid++;
                            else break label;
                        } else break label;
                        break;
                    }
                    case "iyr": {
                        Matcher m = numPattern.matcher(in[1]);
                        if (m.find()) {
                            int num = Integer.parseInt(in[1]);
                            if (num >= 2010 && num <= 2020) valid++;
                            else break label;
                        } else break label;
                        break;
                    }
                    case "eyr": {
                        Matcher m = numPattern.matcher(in[1]);
                        if (m.find()) {
                            int num = Integer.parseInt(in[1]);
                            if (num >= 2020 && num <= 2030) valid++;
                            else break label;
                        } else break label;
                        break;
                    }
                    case "hgt": {
                        Matcher m = heightPattern.matcher(in[1]);
                        if (m.find()) {
                            int num = Integer.parseInt(m.group(1));
                            if (m.group(2).equals("cm")) {
                                if (num >= 150 && num <= 193) valid++;
                                else break label;
                            } else if (m.group(2).equals("in")) {
                                if (num >= 59 && num <= 76) valid++;
                                else break label;
                            } else break label;
                        }
                        break;
                    }
                    case "hcl": {
                        Matcher m = hclPattern.matcher(in[1]);
                        if (m.find()) valid++;
                        else break label;
                        break;
                    }
                    case "ecl": {
                        Matcher m = eclPattern.matcher(in[1]);
                        if (m.find()) valid++;
                        else break label;
                        break;
                    }
                    case "pid": {
                        Matcher m = pidPattern.matcher(in[1]);
                        if (m.find()) valid++;
                        else break label;
                        break;
                    }
                }
            }
            if (valid == 7) {
                total++;
            }
        }
        System.out.println(total);
    }
}
