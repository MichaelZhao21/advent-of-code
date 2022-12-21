import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Day8 {
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
                    .mapToObj(Day8.Tree::create)
                    .toArray(Day8.Tree[]::new);
        }

        for (int i = 0; i < trees.length; i++) {
            trees[i][0].left = -1;
            trees[i][trees[i].length - 1].right = -1;
            for (int j = 1; j < trees[0].length; j++) {
                // Left
                if (trees[i][j - 1].left < trees[i][j - 1].height) {
                    trees[i][j].left = trees[i][j - 1].height;
                } else {
                    trees[i][j].left = trees[i][j - 1].left;
                }

                // Right
                int k = trees[i].length - j - 1;
                if (trees[i][k + 1].right < trees[i][k + 1].height) {
                    trees[i][k].right = trees[i][k + 1].height;
                } else {
                    trees[i][k].right = trees[i][k + 1].right;
                }
            }
        }
        for (int j = 0; j < trees[0].length; j++) {
            trees[0][j].top = -1;
            trees[trees.length - 1][j].bottom = -1;
            for (int i = 1; i < trees.length; i++) {
                // Top
                if (trees[i - 1][j].top < trees[i - 1][j].height) {
                    trees[i][j].top = trees[i - 1][j].height;
                } else {
                    trees[i][j].top = trees[i - 1][j].top;
                }

                // Bottom
                int k = trees.length - i - 1;
                if (trees[k + 1][j].bottom < trees[k + 1][j].height) {
                    trees[k][j].bottom = trees[k + 1][j].height;
                } else {
                    trees[k][j].bottom = trees[k + 1][j].bottom;
                }
            }
        }

        int visible = 0;
        for (int i = 0; i < trees.length; i++) {
            // System.out.println(Arrays.toString(trees[i]));
            for (int j = 0; j < trees[i].length; j++) {
                Tree temp = trees[i][j];
                if (temp.bottom < temp.height ||
                        temp.top < temp.height ||
                        temp.left < temp.height ||
                        temp.right < temp.height) {
                    visible++;
                }
            }
        }

        System.out.println(visible);

        f.close();
    }
}
