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
            if (node.left == null) {// checks does left child is empty
                node.left = new Node(key, value);
                size++;

            } else {
                put(node.left, key, value);// if not we put the value

            }
        } else if (cmp > 0) {
            if (node.right == null) {// checks does right child is empty
                node.right = new Node(key, value);
                size++;

            } else {
                put(node.right, key, value);// if not we put the value
            }
        } else {
            // Update the value if the key already exists
            node.value = value;
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

    public int size() {//checks size of BST
        return size;
    }
    private void inOrder(Node node){//in-order traversal
        if(node !=null){//checks does node is not empty
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }
    public void inOrder(){
        inOrder(root);
    }

    @Override
    public Iterator<elem<K, V>> iterator() {
        return new BinaryIterator();
    }
    private class BinaryIterator implements Iterator<elem<K, V>> {
        private Node current;
        private Stack<Node> stack;

        public BinaryIterator() {
            stack = new Stack<>();
            current = root;
        }
        @Override
        public boolean hasNext() {
            return (current != null || !stack.isEmpty());
        }

        @Override
        public elem<K, V> next() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }

            Node node = stack.pop();
            current = node.right;

            return new elem<>(node.key, node.value);
        }
        public Iterator<elem<K, V>> iterator() {
            return new BinaryIterator();
        }

        public Iterable<K> keys() {
            return new KeyIterable();
        }

        private class KeyIterable implements Iterable<K> {
            @Override
            public Iterator<K> iterator() {
                return new KeyIterator();
            }
        }

        private class KeyIterator implements Iterator<K> {
            private Iterator<elem<K, V>> entryIterator;

            public KeyIterator() {
                entryIterator = BinarySearchTree.this.iterator();
            }

            @Override
            public boolean hasNext() {
                return entryIterator.hasNext();
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return entryIterator.next().key;
            }
        }
    }
}
