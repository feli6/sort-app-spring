package com.brn.sort;

import com.brn.sort.domain.SortResult;
import com.brn.sort.web.ErrorMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class SortAppAcceptanceTest {

    public static final String URL = "/sortNumbers";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldSortValidNumericValues() {
        ResponseEntity<SortResult> responseEntity = restTemplate.postForEntity(URL, "15,19,6,2,3", SortResult.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        SortResult sortresult = responseEntity.getBody();
        assertThat(sortresult).isNotNull();
        assertThat(sortresult.getPositionChanges()).isNotNull();
        assertThat(sortresult.getSortingDuration()).isGreaterThan(0);
        assertThat(sortresult.getSortingOutput()).isEqualTo("2,3,6,15,19");
        assertThat(sortresult.getSortingInput()).isEqualTo("15,19,6,2,3");
    }

    @Test
    public void shouldProcessInputWithSpaces() {
        ResponseEntity<SortResult> responseEntity = restTemplate.postForEntity(URL, "15, 19 ,6 , , 3,,10", SortResult.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        SortResult sortresult = responseEntity.getBody();
        assertThat(sortresult).isNotNull();
        assertThat(sortresult.getPositionChanges()).isNotNull();
        assertThat(sortresult.getSortingDuration()).isGreaterThan(0);
        assertThat(sortresult.getSortingOutput()).isEqualTo("3,6,10,15,19");
        assertThat(sortresult.getSortingInput()).isEqualTo("15,19,6,3,10");
    }

    @Test
    public void shouldReturnErrorCodeWhenEmptyInput() {
        ResponseEntity<ErrorMessage> responseEntity = restTemplate.postForEntity(URL, "", ErrorMessage.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void shouldReturnErrorCodeWhenBadInput() {
        ResponseEntity<ErrorMessage> responseEntity = restTemplate.postForEntity(URL, "afsdfsd", ErrorMessage.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void shouldFetchAllSortingResults() {

        //Create some records
        restTemplate.postForEntity(URL, "15,19,6,2,3", SortResult.class);

        ResponseEntity<SortResult[]> responseEntity = restTemplate.getForEntity(URL,SortResult[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isNotEmpty();
    }
}
