package com.brn.sort.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomSortTest {

    @Test
    public void shouldSortNumbersInAscendingOrder() {
        sortAndAssertResult(new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5});
        sortAndAssertResult(new int[]{2, 3, 6, 19, 15}, new int[]{2, 3, 6, 15, 19});
        sortAndAssertResult(new int[]{1}, new int[]{1});
        sortAndAssertResult(new int[]{1,2,3}, new int[]{1,2,3});
        sortAndAssertResult(new int[]{1,1,2,2,3}, new int[]{1,1,2,2,3});
    }

    private void sortAndAssertResult(int[] numberToSort, int[] expectedResult){
        RandomSort sorter = new RandomSort();
        Sorter.SorterResult result = sorter.sort(numberToSort);
        assertThat(result.getSortedNumbers()).isNotNull();
        assertThat(result.getSortedNumbers()).containsExactly(expectedResult);
        assertThat(result.getSortingDuration()).isGreaterThan(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNullArg() {
        RandomSort sorter = new RandomSort();
        sorter.sort(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEmptyArg() {
        RandomSort sorter = new RandomSort();
        sorter.sort(new int[]{});
    }

}