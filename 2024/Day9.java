import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day9.in"));

        String s = f.readLine();
        int n = s.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - '0';
        }

        f.close();

        // Part 1
        List<Block> bl = new LinkedList<>();
        int curr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                bl.add(new Block(curr++, nums[i]));
            } else {
                bl.add(new Block(-1, nums[i]));
            }
        }

        int i = 0;
        while (i < bl.size()) {
            if (bl.get(i).num != -1) {
                i++;
                continue;
            }

            while (bl.get(bl.size() - 1).num == -1) {
                bl.remove(bl.size() - 1);
            }
            if (bl.size() <= i)
                break;
            int toFill = bl.get(i).count;
            Block b = bl.get(bl.size() - 1);
            if (b.count > toFill) {
                b.count -= toFill;
                bl.get(i).num = b.num;
            } else {
                bl.remove(b);
                toFill -= b.count;
                bl.get(i).count = b.count;
                bl.add(i + 1, new Block(-1, toFill));
            }
            bl.get(i).num = b.num;
            i++;
        }
        ArrayList<Integer> out = new ArrayList<>();
        for (Block block : bl) {
            block.output(out);
        }
        long total = 0;
        for (int j = 0; j < out.size(); j++) {
            total += j * out.get(j);
        }
        System.out.println(total);

        // Part 2
        // THIS DOES NOT RUN IN A REASONABLE AMOUNT OF TIME FOR THE FULL INPUT :(
        bl = new LinkedList<>();
        curr = 0;
        for (i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                bl.add(new Block(curr++, nums[i]));
            } else {
                bl.add(new Block(-1, nums[i]));
            }
        }

        for (int j = bl.size() - 1; j >= 0; j--) {
            if (j % 100 == 0)
            System.out.println(j + "/" + bl.size());
            Block move = bl.get(j);
            if (move.num == -1) continue; // dont move empty
            for (i = 0; i < j; i++) {
                if (bl.get(i).num != -1) continue; // dont move into non empty
                Block b = bl.get(i);
                if (b.count < move.count) continue; // cant move to smaller space
                if (b.count != move.count) { // need to make new block
                    bl.add(i+1, new Block(b.num, b.count - move.count));
                    b.count = move.count;
                    j++;
                }
                b.num = move.num;
                move.num = -1;
                break;
            }
        }
        out = new ArrayList<>();
        for (Block block : bl) {
            block.output(out);
        }
        total = 0;
        for (int j = 0; j < out.size(); j++) {
            total += j * out.get(j);
        }
        System.out.println(total);
    }

    public static class Block {
        public int num;
        public int count;

        public Block(int num, int count) {
            this.num = num;
            this.count = count;
        }

        public void output(List<Integer> il) {
            for (int i = 0; i < count; i++) {
                il.add(num == -1 ? 0 : num);
            }
        }

        @Override
        public String toString() {
            return "(" + num + "," + count + ")";
        }
    }
}
