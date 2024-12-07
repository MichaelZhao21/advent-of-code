import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day7.in"));

        int n = 850;
        // int n = 9;
        long[] tars = new long[n];
        List<Integer>[] nums = new List[n];
        for (int i = 0; i < n; i++) {
            String[] s = f.readLine().split(":");
            tars[i] = Long.parseLong(s[0]);
            nums[i] = Arrays.asList(s[1].trim().split(" ")).stream()
                    .map(String::trim)
                    .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        }

        f.close();

        // Part 1
        long count = 0;
        for (int i = 0; i < n; i++) {
            if (calc(1, nums[i].get(0), tars[i], nums[i])) {
                count += tars[i];
            }
        }
        System.out.println(count);

        // Part 2
        count = 0;
        for (int i = 0; i < n; i++) {
            if (calc2(1, nums[i].get(0), tars[i], nums[i])) {
                count += tars[i];
            }
        }
        System.out.println(count);
    }

    public static boolean calc(int ind, long tot, long tar, List<Integer> items) {
        if (ind == items.size()) {
            return tot == tar;
        }

        boolean a = calc(ind + 1, tot + items.get(ind), tar, items);
        boolean b = calc(ind + 1, tot * items.get(ind), tar, items);
        return a || b;
    }

    public static boolean calc2(int ind, long tot, long tar, List<Integer> items) {
        if (ind == items.size()) {
            return tot == tar;
        }

        boolean a = calc2(ind + 1, tot + items.get(ind), tar, items);
        boolean b = calc2(ind + 1, tot * items.get(ind), tar, items);
        boolean c = calc2(ind + 1, join(tot, items.get(ind)), tar, items);
        return a || b || c;
    }

    public static long join(long a, int b) {
        return Long.parseLong(Long.toString(a) + Integer.toString(b));
    }
}
