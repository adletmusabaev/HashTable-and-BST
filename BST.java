import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size; // New size variable

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }


    // Modify the put method to update size
    private Node put(Node node, K key, V val) {
        if (node == null) return new Node(key, val, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }
        node.size = 1 + size(node.left) + size(node.right); // Update size
        return node;
    }

    // Helper method to get size of a node
    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }


    // New Pair class to hold key and value
    public class Pair {
        private K key;
        private V val;

        public Pair(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() { return key; }
        public V getValue() { return val; }
    }

    // Modified iterator method to return Iterable<Pair>
    public Iterable<Pair> iterator() {
        List<Pair> pairs = new ArrayList<>();
        inorder(root, pairs);
        return pairs;
    }

    // Modified inorder method to add Pair objects to the list
    private void inorder(Node node, List<Pair> pairs) {
        if (node == null) return;
        inorder(node.left, pairs);
        pairs.add(new Pair(node.key, node.val));
        inorder(node.right, pairs);
    }
}