package ychernenko.examples.algoritms.sort;

import java.util.*;

public class HeapSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public List<T> sort(List<T> items) {
        LinkedList<T> result = new LinkedList<>();
        Heap heap = new Heap(items);
        heap.heapify();
        while (!heap.isEmpty()) {
            heap.swapValues(0, heap.size() - 1);
            result.addFirst(heap.popTail());
            heap.siftDown(0);
        }
        return result;
    }


    private class Heap {

        private final List<T> items;

        public Heap(List<T> items) {
            this.items = new ArrayList<>(items);
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }

        public int size() {
            return items.size();
        }

        public void siftDown(int root) {
            if (isEmpty()) {
                return;
            }
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(root);
            while (!deque.isEmpty()) {
                int current = deque.poll();
                Integer childToSwap = selectMaxChild(current);
                if (childToSwap != null && items.get(childToSwap).compareTo(items.get(current)) > 0) {
                    swapValues(childToSwap, current);
                    deque.add(childToSwap);
                }
            }
        }

        private Integer selectMaxChild(int root) {
            int leftChild = 2 * root + 1;
            if (items.size() > leftChild) {
                T leftValue = items.get(leftChild);
                int rightChild = leftChild + 1;
                T rightValue = items.size() > rightChild ? items.get(rightChild) : null;
                return rightValue == null || leftValue.compareTo(rightValue) > 0 ? leftChild : rightChild;
            }
            return null;
        }

        public T popTail() {
            return items.isEmpty() ? null : items.remove(items.size() - 1);
        }

        public void heapify() {
            int leafsStart = items.size() / 2;
            for (int i = leafsStart; i >= 0; i--) {
                siftDown(i);
            }
        }


        public void swapValues(int first, int second) {
            T firstValue = items.get(first);
            items.set(first, items.get(second));
            items.set(second, firstValue);
        }
    }
}
