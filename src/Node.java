/***********************************************
 * @file Node.java
 * @description A generic node class for a binary search tree, with a value as well as left and right children
 * @author Alex Warren
 * @date September 24, 2025
 ***********************************************/

public class Node<E> {
    private E value;
    private Node<E> left;
    private Node<E> right;

    // Constructor to initialize a node
    public Node(E value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Set the value of this node
    public void setElement(E value) {
        this.value = value;
    }

    // Set the left child
    public void setLeft(Node<E> left) {
        this.left = left;
    }

    // Set the right child
    public void setRight(Node<E> right) {
        this.right = right;
    }

    // Get the left child
    public Node<E> getLeft() {
        return left;
    }

    // Get the right child
    public Node<E> getRight() {
        return right;
    }

    // Get the value of the Node
    public E getElement() {
        return value;
    }

    // Determine of node is a leaf
    public boolean isLeaf() {
        return left == null && right == null;
    }
}
