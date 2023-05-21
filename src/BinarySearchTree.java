import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.elem<K, V>> {
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

}



