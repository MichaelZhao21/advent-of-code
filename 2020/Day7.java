package y_2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2020/day7.in"));
        final int n = 594;
        HashMap<String, Bag> bags = new HashMap<>();
        int goldCount = 0;
        for (int i = 0; i < n; i++) {
            String line = f.readLine();
            String iden = line.substring(0, line.indexOf(" bags"));
            String cont = line.substring(line.indexOf("contain ") + 8);
            if (cont.startsWith("no")) {
                bags.put(iden, new Bag(iden, new ArrayList<>(), new ArrayList<>(), false));
            }
            else {
                String[] in = cont.substring(0, cont.length() - 1).split(", ");
                ArrayList<String> children = new ArrayList<>();
                ArrayList<Integer> childrenCount = new ArrayList<>();
                boolean gold = false;
                for (String s : in) {
                    String name = s.substring(2, s.indexOf(" bag"));
                    int num = Integer.parseInt(s.substring(0, 1));
                    children.add(name);
                    childrenCount.add(num);
                    if (name.equals("shiny gold")) {
                        gold = true;
                        goldCount++;
                    }
                }
                bags.put(iden, new Bag(iden, children, childrenCount, gold));
            }
        }

        // Part 1
//        boolean change = true;
//        while (change) {
//            change = false;
//            for (Bag b : bags.values()) {
//                if (!b.hasGold) {
//                    for (String s : b.contents) {
//                        if (bags.get(s).hasGold) {
//                            b.hasGold = true;
//                            goldCount++;
//                            change = true;
//                            break;
//                        }
//                    }
//                }
//            }
//        }

        // Part 2
        Queue<String> dfs = new LinkedList<>();
        ArrayList<String> dfs2 = new ArrayList<>();
        dfs.add("shiny gold");
        dfs2.add("shiny gold");
        while (!dfs.isEmpty()) {
            Bag b = bags.get(dfs.remove());
            if (b.contents.size() != 0) {
                dfs2.addAll(b.contents);
                dfs.addAll(b.contents);
            }
        }
        Collections.reverse(dfs2);
        for (String s : dfs2) {
            Bag b = bags.get(s);
            int bc = 0;
            for (int i = 0; i < b.contents.size(); i++) {
                Bag child = bags.get(b.contents.get(i));
                int count = b.contentCounts.get(i);
                bc += count + count * child.bagCount;
            }
            b.bagCount = bc;
        }
        System.out.println(bags.get("shiny gold").bagCount);

    }
    public static class Bag {
        String color;
        int bagCount = 0;
        ArrayList<String> contents;
        ArrayList<Integer> contentCounts;
        boolean hasGold;
        public Bag(String color, ArrayList<String> contents, ArrayList<Integer> contentCounts, boolean hasGold) {
            this.color = color;
            this.contents = contents;
            this.contentCounts = contentCounts;
            this.hasGold = hasGold;
        }
    }
}
