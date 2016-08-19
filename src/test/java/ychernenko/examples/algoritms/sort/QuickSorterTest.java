package ychernenko.examples.algoritms.sort;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class QuickSorterTest {

    @Test
    public void testSort() {
        int size = 1000;
        List<Integer> items = Stream.generate(() -> (int)(Math.random() * size)).limit(size).collect(toList());
        List<Integer> expected = items.stream().sorted().collect(toList());
        List<Integer> actual = new QuickSorter<Integer>().sort(items);
        assertEquals(expected, actual);
    }
}