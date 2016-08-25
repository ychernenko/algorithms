package ychernenko.examples.algoritms.sort;

import java.util.List;

import ychernenko.examples.algoritms.ds.AVLTree;

public class AVLSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public List<T> sort(List<T> items) {
        AVLTree<T> tree = new AVLTree<>(items);
        return null;
    }

}
