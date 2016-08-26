package ychernenko.examples.algoritms.ds;

import static ychernenko.examples.algoritms.ds.BinaryTreeNode.Direction.*;

public class BinaryTreeNode<T> {

    public enum Direction {
        LEFT,
        RIGHT;

        public Direction opposite() {
            return this == LEFT ? RIGHT : LEFT;
        }
    }

    private final T value;
    private int height;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;

    BinaryTreeNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int getHeight() {
        return height;
    }

    public Direction getDirection(BinaryTreeNode node) {
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

    public BinaryTreeNode<T> getChild(Direction direction) {
        return direction == LEFT ? leftChild : rightChild;
    }

    public void setChild(Direction direction, BinaryTreeNode<T> child) {
        if (direction == LEFT) {
            leftChild = child;
        } else {
            rightChild = child;
        }
        updateHeight();
    }

    public int getChildHeight(Direction direction) {
        BinaryTreeNode<T> child = getChild(direction);
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
