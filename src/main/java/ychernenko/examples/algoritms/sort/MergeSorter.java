package ychernenko.examples.algoritms.sort;

import static java.util.stream.Collectors.*;

import java.util.*;

public class MergeSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public List<T> sort(List<T> items) {
        Deque<List<T>> deque = items.stream().map(Collections::singletonList).collect(toCollection(ArrayDeque::new));
        while (deque.size() > 1) {
            List<T> left = deque.remove();
            List<T> right = deque.remove();
            deque.add(merge(left, right));
        }
        return deque.remove();
    }

    private List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<>(left.size() + right.size());
        Iterator<T> rightIterator = right.iterator();
        T rightItem = rightIterator.hasNext() ? rightIterator.next() : null;
        for (T leftItem : left) {
            while (rightItem != null && leftItem.compareTo(rightItem) >= 0) {
                result.add(rightItem);
                rightItem = rightIterator.hasNext() ? rightIterator.next() : null;
            }
            result.add(leftItem);
        }
        if (rightItem != null) {
            result.add(rightItem);
        }
        rightIterator.forEachRemaining(result::add);
        return result;
    }
}
