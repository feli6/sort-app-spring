package com.brn.sort.service;

import org.springframework.stereotype.Component;

@Component
public class RandomSort implements Sorter {

    public SorterResult sort(int[] input) {
        validate(input);

        final long startTime = System.nanoTime();
        final int positionChanges = randomSort(input);
        final long completionTime = System.nanoTime();
        final long sortingDurationInNanoSeconds = completionTime - startTime;

        return new SorterResult(input, positionChanges, sortingDurationInNanoSeconds);
    }

    private void validate(int[] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Input array shouldn't be empty or null");
        }
    }

    public int randomSort(int[] input) {
        int numberOfSwaps = 0;
        while (!isSorted(input)) {
            swap(input);
            numberOfSwaps++;
        }
        return numberOfSwaps * 2;
    }

    private void swap(int[] input) {
        for (int x = 0; x < input.length; ++x) {
            int i = (int) (Math.random() * input.length);
            int j = (int) (Math.random() * input.length);
            int a = input[i];
            input[i] = input[j];
            input[j] = a;
        }
    }

    private boolean isSorted(int[] i) {
        for (int x = 0; x < i.length - 1; ++x) {
            if (i[x] > i[x + 1]) {
                return false;
            }
        }
        return true;
    }
}
