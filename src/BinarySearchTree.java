import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<K extends Comparable<K>,V> implements Iterable<BinarySearchTree.elem<K, V>> {
    private Node root;
    private int size;

    private class Node {
        private K key;

        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public static class elem<K, V> {
        public K key;
        public V value;

        public elem(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        if (root == null) {// checks does root is empty
            root = new Node(key, value);
            size = 1;
        } else {
            put(root, key, value);
        }
    }

    private void put(Node node, K key, V value) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) { // checks does left child is empty
                node.left = new Node(key, value);
                size++;

            } else {
                put(node.left, key, value); // if not we put the value

            }
        } else if (cmp > 0) {
            if (node.right == null) { // checks does right child is empty
                node.right = new Node(key, value);
                size++;

            } else {
                put(node.right, key, value);
            }
        } else {
            node.value = value;// Update the value if the key already exists
        }
    }

    public void remove(K key) {
        root = deleteNode(root, key);// delete method

    }

    private Node deleteNode(Node node, K key) {
        if (node == null) {//checks does node is empty
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {// checks if middle element is less
            node.left = deleteNode(node.left, key);
        } else if (cmp > 0) {// checks if middle element is more
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
        }
        return node;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return (node != null) ? node.value : null;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {//checks does node is empty
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {// checks if middle element is less
            return getNode(node.left, key);
        } else if (cmp > 0) {// checks if middle element is more
            return getNode(node.right, key);
        } else {
            return node;
        }
    }
    public int size() {
        return size;//checks size of BST
    }
