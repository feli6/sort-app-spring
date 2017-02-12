package com.brn.sort.web;

import com.brn.sort.domain.SortResult;
import com.brn.sort.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sortNumbers")
public class SortNumbersController {

    @Autowired
    private SortingService sortingService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public SortResult sortNumbers(@RequestBody String csvNumbersList) {
        int[] unsortedNumbers = extractNumbersFromCSV(csvNumbersList);
        return sortingService.sortNumbers(unsortedNumbers);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<SortResult> findSortResults() {
        return sortingService.findAllSortResults();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleException(Exception e) {
        return new ErrorMessage("Unable to process the provided input.");
    }

    private int[] extractNumbersFromCSV(String csvNumbersList) {
        validate(csvNumbersList);
        final String[] numbersStringArray = csvNumbersList.split(",");
        return Arrays.stream(numbersStringArray)
                .map(String::trim)
                .filter(this::isNotNullOrEmpty)
                .mapToInt(Integer::valueOf)
                .toArray();
    }

    private boolean isNotNullOrEmpty(String s) {
        return !isNullOrEmpty(s);
    }

    //TODO: More Validation cases need to be implemented
    private void validate(String csvNumbersList) {
        if (isNullOrEmpty(csvNumbersList)) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    private static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
