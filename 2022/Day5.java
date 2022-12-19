import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Day5 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day5.in"));

        // Starting config
        LinkedList<Character>[] lists = (LinkedList<Character>[]) new LinkedList<?>[9];
        lists[0] = new LinkedList<>(Arrays.asList('N', 'H', 'S', 'J', 'F', 'W', 'T', 'D'));
        lists[1] = new LinkedList<>(Arrays.asList('G', 'B', 'N', 'T', 'Q', 'P', 'R', 'H'));
        lists[2] = new LinkedList<>(Arrays.asList('V', 'Q', 'L'));
        lists[3] = new LinkedList<>(Arrays.asList('Q', 'R', 'W', 'S', 'B', 'N'));
        lists[4] = new LinkedList<>(Arrays.asList('B', 'M', 'V', 'T', 'F', 'D', 'N'));
        lists[5] = new LinkedList<>(Arrays.asList('R', 'T', 'H', 'V', 'B', 'D', 'M'));
        lists[6] = new LinkedList<>(Arrays.asList('J', 'Q', 'B', 'D'));
        lists[7] = new LinkedList<>(Arrays.asList('Q', 'H', 'Z', 'R', 'V', 'J', 'N', 'D'));
        lists[8] = new LinkedList<>(Arrays.asList('S', 'M', 'H', 'N', 'B'));

        String line;
        while ((line = f.readLine()) != null) {
            String[] lineSplit = line.split(" ");
            int num = Integer.parseInt(lineSplit[1]);
            int from = Integer.parseInt(lineSplit[3]);
            int to = Integer.parseInt(lineSplit[5]);

            for (int i = 0; i < num; i++) {
                if (lists[from - 1].isEmpty()) {
                    break;
                }
                lists[to - 1].push(lists[from - 1].pop());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(lists[i].pop());
        }
        System.out.println(sb.toString());

        f.close();
    }
}
