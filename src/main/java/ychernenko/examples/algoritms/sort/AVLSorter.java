package ychernenko.examples.algoritms.sort;

import static ychernenko.examples.algoritms.ds.BinaryTreeNode.Direction.*;

import java.util.*;
import java.util.stream.Stream;

import ychernenko.examples.algoritms.ds.AVLTree;
import ychernenko.examples.algoritms.ds.BinaryTreeNode;

public class AVLSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public List<T> sort(List<T> items) {
        return traverse(new AVLTree<>(items).getRoot());
    }

    private List<T> traverse(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        Deque<NodeHolder<T>> deque = new ArrayDeque<>();
        deque.add(new NodeHolder<>(root));
        while (!deque.isEmpty()) {
            NodeHolder<T> node = deque.remove();
            if (node.visited) {
                result.add(node.node.getValue());
            } else {
                node.visited = true;
                Stream.of(
                        node.getChild(RIGHT),
                        node,
                        node.getChild(LEFT))
                      .filter(Objects::nonNull)
                      .forEach(deque::push);
            }
        }
        return result;
    }

    private static class NodeHolder<T> {

        public final BinaryTreeNode<T> node;
        public boolean visited;

        public NodeHolder(BinaryTreeNode<T> node) {
            this.node = node;
        }

        public NodeHolder<T> getChild(BinaryTreeNode.Direction direction) {
            BinaryTreeNode<T> child = node.getChild(direction);
            return child == null ? null : new NodeHolder<>(child);
        }
    }

}
