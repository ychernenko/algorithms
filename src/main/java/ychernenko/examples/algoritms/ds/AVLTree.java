package ychernenko.examples.algoritms.ds;

import static ychernenko.examples.algoritms.ds.BinaryTreeNode.Direction.*;

import java.util.ArrayList;
import java.util.List;

import ychernenko.examples.algoritms.ds.BinaryTreeNode.Direction;

public class AVLTree<T extends Comparable<T>> {

    private BinaryTreeNode<T> root;

    public AVLTree(Iterable<T> items) {
        addAll(items);
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public void addAll(Iterable<T> items) {
        items.forEach(this::add);
    }

    public void add(T item) {
        BinaryTreeNode<T> newNode = new BinaryTreeNode<>(item);
        if (root == null) {
            root = newNode;
        } else {
            BinaryTreeNode<T> current = root;
            List<BinaryTreeNode<T>> ancestors = new ArrayList<>();
            while (current != newNode) {
                ancestors.add(current);
                Direction direction = current.getValue().compareTo(newNode.getValue()) > 0
                        ? LEFT
                        : RIGHT;
                BinaryTreeNode<T> child = current.getChild(direction);
                if (child == null) {
                    current.setChild(direction, newNode);
                    child = newNode;
                }
                current = child;
            }
            balanceAncestors(ancestors);
        }
    }

    private void balanceAncestors(List<BinaryTreeNode<T>> ancestors) {
        for (int i = ancestors.size() - 2; i >= 0; i--) {
            BinaryTreeNode<T> current = ancestors.get(i);
            current.updateHeight();
            if (!isBalanced(current)) {
                BinaryTreeNode<T> newRoot = balance(current);
                int parentIndex = i - 1;
                if (parentIndex < 0) {
                    root = newRoot;
                } else {
                    BinaryTreeNode<T> parent = ancestors.get(parentIndex);
                    parent.setChild(parent.getDirection(current), newRoot);
                }
            }
        }
    }

    private boolean isBalanced(BinaryTreeNode node) {
        return Math.abs(node.getBalance()) < 2;
    }

    private BinaryTreeNode<T> balance(BinaryTreeNode<T> node) {
        for (Direction direction : values()) {
            if (node.getChildHeight(direction) - node.getChildHeight(direction.opposite()) > 1) {
                BinaryTreeNode<T> child = node.getChild(direction);
                if (child.isHeavy(direction.opposite())) {
                    BinaryTreeNode<T> newChild = rotate(child, direction);
                    node.setChild(direction, newChild);
                }
                return rotate(node, direction.opposite());
            }
        }
        return node;
    }

    private BinaryTreeNode<T> rotate(BinaryTreeNode<T> node, Direction direction) {
        BinaryTreeNode<T> newRoot = node.getChild(direction.opposite());
        BinaryTreeNode<T> innerNode = newRoot.getChild(direction);
        node.setChild(direction.opposite(), innerNode);
        newRoot.setChild(direction, node);
        return newRoot;
    }


}
