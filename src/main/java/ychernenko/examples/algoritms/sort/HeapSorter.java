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

        public void siftDown(Integer root) {
            if (isEmpty()) {
                return;
            }
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(root);
            while (!deque.isEmpty()) {
                Integer current = deque.poll();
                for (int i = 0; i < 2; i++) {
                    int child = getLeftChild(current) + i;
                    if (items.size() > child && items.get(child).compareTo(items.get(current)) > 0) {
                        swapValues(child, current);
                        deque.add(child);
                    }
                }
            }
        }

        public T popTail() {
            return items.isEmpty() ? null : items.remove(items.size() - 1);
        }

        public void heapify() {
            int leafsStart = items.size() / 2;
            for (Integer i = leafsStart; i >= 0; i--) {
                siftDown(i);
            }
        }


        public int getLeftChild(int i) {
            return 2 * i + 1;
        }

        public void swapValues(Integer first, Integer second) {
            T firstValue = items.get(first);
            items.set(first, items.get(second));
            items.set(second, firstValue);
        }
    }
}
