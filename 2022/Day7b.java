import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Day7b {
    public static class Node {
        public Node parent;
        public LinkedList<Node> children;
        public long size;
        public String name;

        public Node(String name, Node parent) {
            this.children = new LinkedList<>();
            this.name = name;
            this.size = 0;
            this.parent = parent;
        }

        // File node if no children list
        public Node(String name, Node parent, long size) {
            this.children = null;
            this.name = name;
            this.size = size;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object obj) {
            Node n = (Node) obj;
            return n.name.equals(name);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("day7.in"));

        // Ignore first `$ cd /`
        f.readLine();

        String line;
        Node root = new Node("/", null);
        Node curr = root;
        while ((line = f.readLine()) != null) {
            String[] lineSplit = line.split(" ");
            if (lineSplit[0].equals("$")) {
                // 2 commands
                if (lineSplit[1].equals("cd")) {
                    // cd
                    if (lineSplit[2].equals("..")) {
                        curr = curr.parent;
                    } else {
                        int in = curr.children.indexOf(new Node(lineSplit[2], null));
                        if (in == -1) {
                            throw new IndexOutOfBoundsException("fuck");
                        } else {
                            curr = curr.children.get(in);
                        }
                    }
                } else {
                    // ls - honestly just ignore this
                }
            } else {
                // ls output
                if (lineSplit[0].equals("dir")) {
                    // dir listing
                    curr.children.add(new Node(lineSplit[1], curr));
                } else {
                    // file listing
                    long size = Long.parseLong(lineSplit[0]);
                    curr.children.add(new Node(lineSplit[1], curr, size));
                }
            }
        }

        // DFS through tree and get sizes
        updateSize(root);

        // Space needed
        long minSpace = 30000000 - (70000000 - root.size);

        System.out.println(minSpace);

        PriorityQueue<Long> heap = new PriorityQueue<>();
        collectAllDirs(root, heap);

        long size = -1;
        while (!heap.isEmpty()) {
            long cur = heap.poll();
            if (minSpace <= cur) {
                size = cur;
                break;
            }
        }

        System.out.println(size);

        f.close();
    }

    public static long updateSize(Node node) {
        // File node
        if (node.children == null)
            return node.size;

        // Dir node
        long totalSize = 0;
        for (Node n : node.children) {
            totalSize += updateSize(n);
        }
        node.size = totalSize;

        return totalSize;
    }

    public static void collectAllDirs(Node node, PriorityQueue<Long> heap) {
        if (node.children == null) return;

        for (Node n : node.children) {
            collectAllDirs(n, heap);
        }

        heap.offer(node.size);
    }

}
