package com.brn.sort.service;


import com.brn.sort.domain.SortResult;
import com.brn.sort.domain.SortResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.copyOf;

@Service
public class SortingService {

    @Autowired
    private Sorter sorter;

    @Resource
    private final SortResultRepository repository;

    public SortingService(SortResultRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SortResult sortNumbers(int[] inputNumbers) {
        Sorter.SorterResult sorterResult = sorter.sort(copyOf(inputNumbers, inputNumbers.length));
        return repository.save(toSortResult(inputNumbers, sorterResult));
    }

    @Transactional
    public List<SortResult> findAllSortResults() {
        return repository.findAll();
    }

    /**
     * Converts to entity SortResult
     *
     * @param numbers
     * @param sorterResult
     * @return
     */
    private SortResult toSortResult(int[] numbers, Sorter.SorterResult sorterResult) {
        return new SortResult(toCsv(numbers), toCsv(sorterResult.getSortedNumbers()), sorterResult.getSortingDuration(), sorterResult.getPositionChanges());
    }

    private static String toCsv(int[] array) {
        return Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }
}
