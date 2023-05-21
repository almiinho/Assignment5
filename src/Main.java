public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> BST = new BinarySearchTree<>();

        BST.put(1, "20");
        BST.put(2, "19");
        BST.put(5, "7");
        BST.put(6, "14");
        BST.put(4, "3");
        BST.put(3, "2");
        BST.put(7, "88");
        for (BinarySearchTree.elem<Integer, String> elem : BST) {
            System.out.println("key is " + elem.key + " and value is " + elem.value);
        }
        System.out.println(BST.get(2));
        System.out.println(BST.size());
        BST.remove(4);
    }
}