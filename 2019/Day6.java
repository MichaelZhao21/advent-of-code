package y_2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("src/y_2019/day6.in"));
        final int n = 1350;
        HashMap<String, Planet> planets = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] rawIn = f.readLine().split("\\)");
            Planet p1 = planets.get(rawIn[0]);
            Planet p2 = planets.get(rawIn[1]);
            if (p1 == null) {
                Planet p = new Planet(null, rawIn[0]);
                p.children.add(rawIn[1]);
                planets.put(rawIn[0], p);
            }
            else {
                planets.get(rawIn[0]).children.add(rawIn[1]);
            }
            if (p2 == null) {
                Planet p = new Planet(rawIn[0], rawIn[1]);
                planets.put(rawIn[1], p);
            }
            else {
                planets.get(rawIn[1]).parent = rawIn[0];
            }
        }

        // Part 1
        Queue<Planet> dfs = new LinkedList<>();
        dfs.add(planets.get("COM"));
        int totalOrbits = 0;
        while (!dfs.isEmpty()) {
            Planet p = dfs.remove();
            if (p.parent != null) p.orbits += planets.get(p.parent).orbits + 1;
            for (String s : p.children) {
                dfs.add(planets.get(s));
            }
            totalOrbits += p.orbits;
        }
        System.out.println(totalOrbits);

        // Part 2
        ArrayList<String> youPath = new ArrayList<>();
        ArrayList<String> sanPath = new ArrayList<>();

        youPath.add("YOU");
        String currPar = "YOU";
        while (!currPar.equals("COM")) {
            currPar = planets.get(currPar).parent;
            youPath.add(currPar);
        }
        sanPath.add("SAN");
        currPar = "SAN";
        while (!currPar.equals("COM")) {
            currPar = planets.get(currPar).parent;
            sanPath.add(currPar);
        }

        Collections.reverse(youPath);
        Collections.reverse(sanPath);

        Planet common = null;
        for (int i = 0; i < Math.min(youPath.size(), sanPath.size()); i++) {
            if (!youPath.get(i).equals(sanPath.get(i))) {
                common = planets.get(youPath.get(i - 1));
                break;
            }
        }
        if (common == null) System.out.println("uh oh");
        else {
            System.out.println(planets.get("YOU").orbits + planets.get("SAN").orbits - 2 * common.orbits - 2);
        }
    }
    public static class Planet {
        ArrayList<String> children = new ArrayList<>();
        String parent;
        String id;
        int orbits = 0;
        public Planet(String parent, String id) {
            this.parent = parent;
            this.id = id;
        }
    }
}
