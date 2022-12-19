import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Day6 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day6.in"));

        String l = f.readLine();
        char[] input = l.toCharArray();

        LinkedList<Character> chars = new LinkedList<>(Arrays.asList(input[0], input[1], input[2], input[3]));
        
        int lastRep = 1;
        for (int i = 4; i < input.length; i++) {
            char curr = input[i];

            lastRep--;
            chars.pop();
            for (int j = 0; j < chars.size(); j++) {
                if (curr == chars.get(j) && j > lastRep) {
                    lastRep = j;
                }
            }
            chars.add(curr);

            if (lastRep < 0) {
                System.out.println(i + 1);
                break;
            }
        }

        f.close();
    }
}
