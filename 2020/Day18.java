import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day18 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day18.in"));

        int n = 376;
        String[] in = new String[n];
        for (int i = 0; i < n; i++) {
            in[i] = f.readLine();
        }

        f.close();

        // Part 1
        long total = 0;
        long a, b;
        String op, prev;
        for (int i = 0; i < n; i++) {
            List<String> ss = split(in[i]);
            Stack<String> st = new Stack<>();
            for (String s : ss) {
                switch (s) {
                    case "+":
                    case "*":
                    case "(":
                        st.push(s);
                        break;
                    case ")":
                        a = Long.parseLong(st.pop());
                        st.pop(); // open (
                        if (st.size() == 0 || st.peek().equals("(")) {
                            st.add(Long.toString(a));
                            continue;
                        }
                        op = st.pop();
                        b = Long.parseLong(st.pop());
                        st.push(calc(op, a, b));
                        break;
                    default:
                        if (st.size() == 0) {
                            st.push(s);
                            continue;
                        }
                        prev = st.peek();
                        if (!prev.equals("+") && !prev.equals("*")) {
                            st.push(s);
                        } else {
                            a = Long.parseLong(s);
                            op = st.pop();
                            b = Long.parseLong(st.pop());
                            st.push(calc(op, a, b));
                        }
                        break;
                }
            }
            total += Long.parseLong(st.pop());
        }
        System.out.println(total);

        // Part 2
        total = 0;
        for (int i = 0; i < n; i++) {
            List<String> ss = split(in[i]);
            Stack<String> st = new Stack<>();
            for (String s : ss) {
                switch (s) {
                    case "+":
                    case "*":
                    case "(":
                        st.push(s);
                        break;
                    case ")":
                        a = Long.parseLong(st.pop());
                        String c = st.pop(); // open (
                        while (c.equals("*")) { // Mult loop
                            a *= Long.parseLong(st.pop());
                            c = st.pop();
                        }
                        if (st.size() == 0 || st.peek().equals("(")) {
                            st.add(Long.toString(a));
                            break;
                        }
                        op = st.pop();
                        if (op.equals("*")) {
                            st.push(op);
                            st.push(Long.toString(a));
                            break;
                        }
                        b = Long.parseLong(st.pop());
                        st.push(calc(op, a, b));
                        break;
                    default:
                        if (st.size() == 0) {
                            st.push(s);
                            continue;
                        }
                        prev = st.peek();
                        if (!prev.equals("+") && !prev.equals("*")) {
                            st.push(s);
                        } else {
                            a = Long.parseLong(s);
                            op = st.pop();
                            if (op.equals("*")) {
                                st.push(op);
                                st.push(Long.toString(a));
                                break;
                            }
                            b = Long.parseLong(st.pop());
                            st.push(calc(op, a, b));
                        }
                        break;
                }
            }
            while (st.size() > 1) {
                a = Long.parseLong(st.pop());
                if (!st.pop().equals("*")) System.out.println("UH OH");
                b = Long.parseLong(st.pop());
                st.add(Long.toString(a * b));
            }
            total += Long.parseLong(st.pop());
        }
        System.out.println(total);
    }

    public static String calc(String op, long a, long b) {
        if (op.equals("+"))
            return Long.toString(a + b);
        return Long.toString(a * b);
    }

    public static List<String> split(String s) {
        String[] ts = s.split(" ");
        List<String> out = new ArrayList<>();
        for (String ss : ts) {
            if (ss.length() == 1)
                out.add(ss);
            else {
                boolean allDigit = true;
                for (char c : ss.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        allDigit = false;
                        break;
                    }
                }
                if (allDigit)
                    out.add(ss);
                else {
                    // Split parenthesis and numbers
                    String curr = "";
                    for (char c : ss.toCharArray()) {
                        if (Character.isDigit(c)) {
                            curr += c;
                        } else {
                            if (curr != "") {
                                out.add(curr);
                                curr = "";
                            }
                            out.add("" + c);
                        }
                    }
                    if (curr != "")
                        out.add(curr);
                }
            }
        }
        return out;
    }
}
