package ychernenko.examples.algoritms.sort;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class SorterTest {

    private final int size = 10000;
    private final List<Integer> items = Stream.generate(() -> (int)(Math.random() * size)).limit(size).collect(toList());
    private final List<Integer> expected = items.stream().sorted().collect(toList());

    private void test(Sorter<Integer> sorter) {
        assertEquals(expected, sorter.sort(new ArrayList<>(items)));
    }

    @Test
    public void testQuickSort() {
        test(new QuickSorter<>());
    }

    @Test
    public void testMergeSort() {
        test(new MergeSorter<>());
    }

    @Test
    public void testHeapSort() {
        test(new HeapSorter<>());
    }
}