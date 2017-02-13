package com.brn.sort.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomSortTest {

    @Test
    public void shouldSortNumbersInAscendingOrder() {
        RandomSort sorter = new RandomSort();
        Sorter.SorterResult result = sorter.sort(new int[]{5, 4, 3, 2, 1});
        assertThat(result.getSortedNumbers()).isNotNull();
        assertThat(result.getSortedNumbers()).containsExactly(1, 2, 3, 4, 5);
        assertThat(result.getSortingDuration()).isGreaterThan(0);
    }

    @Test
    public void shouldSortNumbersInAscendingOrder2() {
        RandomSort sorter = new RandomSort();
        Sorter.SorterResult result = sorter.sort(new int[]{2, 3, 6, 19, 15});
        assertThat(result.getSortedNumbers()).isNotNull();
        assertThat(result.getSortedNumbers()).containsExactly(2, 3, 6, 15, 19);

        result = sorter.sort(new int[]{5, 2, 3, 6, 12, 7, 14, 9, 10, 11});
        assertThat(result.getSortedNumbers()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenBabArg() {
        RandomSort sorter = new RandomSort();
        Sorter.SorterResult result = sorter.sort(null);
    }

}