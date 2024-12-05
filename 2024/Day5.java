import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Day5 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day5.in"));

        // int n = 21;
        // int m = 6;
        int n = 1176;
        int m = 214;
        HashMap<Integer, HashSet<Integer>> rules = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] ss = f.readLine().split("\\|");
            int a = Integer.parseInt(ss[0]);
            if (!rules.containsKey(a)) {
                HashSet<Integer> hs = new HashSet<>();
                hs.add(Integer.parseInt(ss[1]));
                rules.put(a, hs);
            } else {
                HashSet<Integer> hs = rules.get(a);
                hs.add(Integer.parseInt(ss[1]));
                rules.put(a, hs);
            }
        }

        f.readLine();

        String[] tests = new String[m];
        for (int i = 0; i < m; i++) {
            tests[i] = f.readLine();
        }

        f.close();

        // Part 1
        ArrayList<String> wrong = new ArrayList<>(); // for part 2
        int sum = 0;
        for (String t : tests) {
            int[] nums = Arrays.asList(t.split(",")).stream()
                    .map(String::trim)
                    .mapToInt(Integer::parseInt).toArray();
            boolean good = true;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (rules.containsKey(nums[j]) && rules.get(nums[j]).contains(nums[i])) {
                        good = false;
                        break;
                    }
                }
            }
            if (good) {
                sum += nums[nums.length / 2];
            } else {
                wrong.add(t); // for part 2
            }
        }
        System.out.println(sum);

        // Part 2
        sum = 0;
        for (String test : wrong) {
            int[] nums = Arrays.asList(test.split(",")).stream()
                    .map(String::trim)
                    .mapToInt(Integer::parseInt).toArray();

            // Create topo sort
            HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
            ArrayList<Integer> sorted = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                graph.put(nums[j], new HashSet<>());
            }
            for (int i = 0; i < nums.length; i++) {
                if (!rules.containsKey(nums[i]))
                    continue;
                for (int j = 0; j < nums.length; j++) {
                    if (rules.get(nums[i]).contains(nums[j])) {
                        HashSet<Integer> hs = graph.get(nums[j]);
                        hs.add(nums[i]);
                        graph.put(nums[j], hs);
                    }
                }
            }
            while (graph.size() > 0) {
                int found = -1;
                for (Map.Entry<Integer, HashSet<Integer>> entry : graph.entrySet()) {
                    if (entry.getValue().size() == 0) {
                        found = entry.getKey();
                        break;
                    }
                }
                if (found == -1) {
                    System.out.println("ERROR DOING TOPO SORT: " + graph + " " + test);
                    System.exit(1);
                }
                graph.remove(found);
                sorted.add(found);
                for (Map.Entry<Integer, HashSet<Integer>> entry : graph.entrySet()) {
                    entry.getValue().remove(found);
                }
            }
            sum += sorted.get(sorted.size() / 2);
        }
        System.out.println(sum);
    }
}
