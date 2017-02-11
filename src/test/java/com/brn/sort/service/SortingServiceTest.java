package com.brn.sort.service;


import com.brn.sort.domain.SortResult;
import com.brn.sort.domain.SortResultRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SortingServiceTest {

    private final SortResultRepository repository = mock(SortResultRepository.class);

    private final SortingService target = new SortingService(repository);

    @Test
    public void shouldSortNumbers() {
        when(repository.save(any(SortResult.class))).thenReturn(new SortResult("1,2,3", "2,3,4", 1,2));
        SortResult result = target.sortNumbers(new int[]{2, 3, 4, 5, 6});
        assertThat(result).isNotNull();
    }

}