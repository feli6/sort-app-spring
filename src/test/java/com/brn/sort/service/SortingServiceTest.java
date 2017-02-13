package com.brn.sort.service;


import com.brn.sort.domain.SortResult;
import com.brn.sort.domain.SortResultRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortingServiceTest {

    @MockBean
    private SortResultRepository repository;

    @MockBean
    private  Sorter sorter;

    @Autowired
    private SortingService service;

    @Test
    public void shouldSortNumbers() {
        when(sorter.sort(any())).thenReturn(new Sorter.SorterResult(new int[]{1,2,3,4,5}, 1, 2));
        when(repository.save(any(SortResult.class))).thenReturn(new SortResult("1,2,3", "2,3,4", 1,2));
        SortResult result = service.sortNumbers(new int[]{2, 3, 4, 5, 6});
        assertThat(result).isNotNull();
    }

}