import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day4.in"));

        String line;
        int count = 0;
        while ((line = f.readLine()) != null) {
            String[] lineSplit = line.split(",");
            String[] a = lineSplit[0].split("-");
            String[] b = lineSplit[1].split("-");
            int aa = Integer.parseInt(a[0]);
            int ab = Integer.parseInt(a[1]);
            int ba = Integer.parseInt(b[0]);
            int bb = Integer.parseInt(b[1]);
            if ((aa <= ba && ab >= bb) || (ba <= aa && bb >= ab)) {
                count++;
            }
        }

        System.out.println(count);
        f.close();
    }
}
