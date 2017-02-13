package com.brn.sort.service;


import com.brn.sort.domain.SortResult;
import com.brn.sort.domain.SortResultRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SortingServiceTest {

    @MockBean
    private SortResultRepository repository;

    private final SortingService target = new SortingService(repository);

    @Test
    public void shouldSortNumbers() {
        when(repository.save(any(SortResult.class))).thenReturn(new SortResult("1,2,3", "2,3,4", 1, 2));
        SortResult result = target.sortNumbers(new int[]{2, 3, 4, 5, 6});
        assertThat(result).isNotNull();
    }

}