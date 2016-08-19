package ychernenko.examples.algoritms.sort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class QuickSorterTest {

    @Test
    public void testSort() {
        int size = 100;
        List<Integer> items = Stream.generate(() -> (int)(Math.random() * size)).limit(size).collect(Collectors.toList());
        List<Integer> expected = new ArrayList<>(items);
        Collections.sort(expected);
        List<Integer> actual = new QuickSorter<Integer>().sort(items);
        assertEquals(expected, actual);
    }
}