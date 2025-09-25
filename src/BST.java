/***********************************************
 * @file BST.java
 * @description A generic BST implementation with the functions
 *              insert, remove, search, clear, size, and
 *              iteration with an internal iterator class.
 * @author Alex Warren
 * @date September 18, 2025
 ***********************************************/

import java.util.Iterator;
import java.util.Stack;

public class BST<E extends Comparable<E>> implements Iterable<E> {
    private Node<E> root;
    private int nodecount;

    // Constructor to initialize an empty BST
    public BST() {
        root = null;
        nodecount = 0;
    }

    // Implement the clear method
    public void clear() {
        root = null;
        nodecount = 0;
    }

    // Implement the size method
    public int size() {
        return nodecount;
    }

    // Insert a new element into the BST
    public void insert(E e) {
        root = insertHelp(root, e);
        nodecount++;
    }

    // Search for an element in the BST
    public E search(E key) {
        return searchHelp(root, key);
    }

    // Remove an element from the BST
    public E remove(E key) {
        E temp = searchHelp(root, key);
        if (temp != null) {
            root = removeHelp(root, key);
            nodecount--;
        }
        return temp;
    }

    // Recursive helper method for search
    private E searchHelp(Node<E> rt, E key) {
        if (rt == null) return null;

        int temp = rt.getElement().compareTo(key);

        if (temp > 0) {
            return searchHelp(rt.getLeft(), key);
        }
        else if (temp == 0) {
            return rt.getElement();
        }
        else {
            return searchHelp(rt.getRight(), key);
        }
    }

    // Recursive helper method for insert
    private Node<E> insertHelp(Node<E> rt, E key) {
        if (rt == null)
            return new Node<E>(key);
        if (rt.getElement().compareTo(key) == 0) {
            return rt;
        }
        if (rt.getElement().compareTo(key) > 0) {
            rt.setLeft(insertHelp(rt.getLeft(), key));
        }
        else {
            rt.setRight(insertHelp(rt.getRight(), key));
        }
        return rt;
    }

    // find maximum value in BST
    private Node<E> getMax(Node<E> rt) {
        while (rt.getRight() != null) {
            rt = rt.getRight();
        }
        return rt;
    }

    // remove maximum value in BST
    private Node<E> deleteMax(Node<E> rt) {
        if (rt.getRight() == null) {
            return rt.getLeft();
        }
        rt.setRight(deleteMax(rt.getRight()));
        return rt;
    }

    // Recursive helper method for remove
    private Node<E> removeHelp(Node<E> rt, E key) {

        // empty case
        if (rt == null) {
            return null;
        }

        // if element is larger than key
        if (rt.getElement().compareTo(key) > 0) {
            rt.setLeft(removeHelp(rt.getLeft(), key));
        }

        // if element is less than key
        else if (rt.getElement().compareTo(key) < 0) {
            rt.setRight(removeHelp(rt.getRight(), key));
        }

        // if element is equal to key
        else {
            if (rt.getLeft() == null)
                return rt.getRight();
            else if (rt.getRight() == null)
                return rt.getLeft();
            else {
                Node<E> temp = getMax(rt.getLeft());
                rt.setElement(temp.getElement());
                rt.setLeft(deleteMax(rt.getLeft()));
            }
        }
        return rt;
    }

    @Override
    // Return inorder iterader for BST
    public Iterator<E> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<E> {
        private Stack<Node<E>> stack = new Stack<>();

        // initialize iterator
        public BSTIterator(Node<E> root) {
            pushLeft(root);
        }

        // Push left descendants onto stack
        private void pushLeft(Node<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        // Check if more elements
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        // Return next element and update stack
        public E next() {
            Node<E> node = stack.pop();
            E result = node.getElement();
            if (node.getRight() != null) {
                pushLeft(node.getRight());
            }
            return result;
        }
    }
}