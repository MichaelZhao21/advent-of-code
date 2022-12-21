import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Day8b {
    public static class Tree {
        int height;
        int left;
        int right;
        int top;
        int bottom;

        public Tree(int height) {
            this.height = height;
            this.left = 0;
            this.right = 0;
            this.bottom = 0;
            this.top = 0;
        }

        public static Tree create(int height) {
            return new Tree(height);
        }

        @Override
        public String toString() {
            return String.format("%d[%d, %d, %d, %d]", height, top, right, bottom, left);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day8.in"));

        LinkedList<String> rawList = new LinkedList<>();
        String line;
        while ((line = f.readLine()) != null) {
            rawList.add(line);
        }

        Tree[][] trees = new Tree[rawList.size()][rawList.get(0).length()];
        int index = 0;
        for (String s : rawList) {
            trees[index++] = Arrays.stream(s.split(""))
                    .mapToInt(Integer::parseInt)
                    .mapToObj(Day8b.Tree::create)
                    .toArray(Day8b.Tree[]::new);
        }

        // Ignore borders
        long max = 0;
        for (int i = 1; i < trees.length - 1; i++) {
            for (int j = 1; j < trees[i].length - 1; j++) {
                // Top
                int offset = 1;
                while (i - offset >= 0 && trees[i - offset][j].height < trees[i][j].height) {
                    trees[i][j].top++;
                    offset++;
                }
                if (i - offset >= 0)
                    trees[i][j].top++;

                // Bottom
                offset = 1;
                while (i + offset < trees.length && trees[i + offset][j].height < trees[i][j].height) {
                    trees[i][j].bottom++;
                    offset++;
                }
                if (i + offset < trees.length)
                    trees[i][j].bottom++;

                // Left
                offset = 1;
                while (j - offset >= 0 && trees[i][j - offset].height < trees[i][j].height) {
                    trees[i][j].left++;
                    offset++;
                }
                if (j - offset >= 0)
                    trees[i][j].left++;

                // Right
                offset = 1;
                while (j + offset < trees[i].length && trees[i][j + offset].height < trees[i][j].height) {
                    trees[i][j].right++;
                    offset++;
                }
                if (j + offset < trees[i].length)
                    trees[i][j].right++;

                // Scenic value
                // System.out.printf("(%d,%d): %s\n", i, j, trees[i][j]);
                long score = ((long) trees[i][j].left) * trees[i][j].right * trees[i][j].top * trees[i][j].bottom;
                if (score > max) {
                    max = score;
                }
            }
        }

        System.out.println(max);

        f.close();
    }
}
