package ychernenko.examples.algoritms.sort;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class SorterTest {

    private final int size = 10000;
    private final List<Integer> items = Stream.generate(() -> (int)(Math.random() * size)).limit(size).collect(toList());
    private final List<Integer> expected = items.stream().sorted().collect(toList());

    @Test
    public void testQuickSort() {
        Sorter<Integer> sorter = new QuickSorter<>();
        assertEquals(expected, sorter.sort(items));
    }

    @Test
    public void testMergeSort() {
        Sorter<Integer> sorter = new MergeSorter<>();
        assertEquals(expected, sorter.sort(items));
    }
}