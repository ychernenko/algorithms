package ychernenko.examples.algoritms.ds;

import static ychernenko.examples.algoritms.ds.AVLTree.Direction.*;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {

    public enum Direction {
        LEFT,
        RIGHT;

        public Direction opposite() {
            return this == LEFT ? RIGHT : LEFT;
        }
    }

    public AVLTree(Iterable<T> items) {
        addAll(items);
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void addAll(Iterable<T> items) {
        items.forEach(this::add);
    }

    public void add(T item) {
        Node newNode = new Node(item);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            List<Node> ancestors = new ArrayList<>();
            while (current != newNode) {
                ancestors.add(current);
                Direction direction = current.getValue().compareTo(newNode.getValue()) > 0
                        ? LEFT
                        : RIGHT;
                Node child = current.getChild(direction);
                if (child == null) {
                    current.setChild(direction, newNode);
                    child = newNode;
                }
                current = child;
            }
            balanceAncestors(ancestors);
        }
    }

    private void balanceAncestors(List<Node> ancestors) {
        for (int i = ancestors.size() - 2; i >= 0; i--) {
            Node current = ancestors.get(i);
            current.updateHeight();
            if (!isBalanced(current)) {
                Node newRoot = balance(current);
                int parentIndex = i - 1;
                if (parentIndex < 0) {
                    root = newRoot;
                } else {
                    Node parent = ancestors.get(parentIndex);
                    parent.setChild(parent.getDirection(current), newRoot);
                }
            }
        }
    }

    public boolean isBalanced(Node node) {
        return Math.abs(node.getBalance()) < 2;
    }

    private Node balance(Node node) {
        for (Direction direction : values()) {
            if (node.getChildHeight(direction) - node.getChildHeight(direction.opposite()) > 1) {
                Node child = node.getChild(direction);
                if (child.isHeavy(direction.opposite())) {
                    Node newChild = rotate(child, direction);
                    node.setChild(direction, newChild);
                }
                return rotate(node, direction.opposite());
            }
        }
        return node;
    }

    private Node rotate(Node node, Direction direction) {
        Node newRoot = node.getChild(direction.opposite());
        Node innerNode = newRoot.getChild(direction);
        node.setChild(direction.opposite(), innerNode);
        newRoot.setChild(direction, node);
        return newRoot;
    }


    private class Node {

        private final T value;
        private int height;
        private Node leftChild;
        private Node rightChild;

        private Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public int getHeight() {
            return height;
        }

        public Direction getDirection(Node node) {
            return leftChild == node ? LEFT :
                    rightChild == node ? RIGHT :
                            null;
        }

        public int getBalance() {
            return getChildHeight(RIGHT) - getChildHeight(LEFT);
        }

        public void updateHeight() {
            int leftHeight = getChildHeight(LEFT);
            int rightHeight = getChildHeight(RIGHT);
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public Node getChild(Direction direction) {
            return direction == LEFT ? leftChild : rightChild;
        }

        public void setChild(Direction direction, Node child) {
            if (direction == LEFT) {
                leftChild = child;
            } else {
                rightChild = child;
            }
            updateHeight();
        }

        public int getChildHeight(Direction direction) {
            Node child = getChild(direction);
            return child == null ? 0 : child.getHeight();
        }

        public boolean isHeavy(Direction direction) {
            return getChildHeight(direction) > getChildHeight(direction.opposite());
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

}
