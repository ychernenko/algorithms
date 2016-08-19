package ychernenko.examples.algoritms.sort;

import java.util.*;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    public List<T> sort(List<T> items) {
        List<T> result = new ArrayList<>(items.size());
        Stack<List<T>> stack = new Stack<>();
        stack.push(items);
        while (!stack.isEmpty()) {
            List<T> subList = stack.pop();
            if (subList.size() == 1) {
                result.addAll(subList);
            } else {
                T pivot = subList.get(0);
                List<T> left = new ArrayList<>();
                List<T> right = new ArrayList<>();
                subList.stream().skip(1).forEach((item) -> (pivot.compareTo(item) > 0 ? left : right).add(item));
                if (!right.isEmpty()) {
                    stack.push(right);
                }
                stack.push(Collections.singletonList(pivot));
                if (!left.isEmpty()) {
                    stack.push(left);
                }
            }
        }
        return result;
    }
}
