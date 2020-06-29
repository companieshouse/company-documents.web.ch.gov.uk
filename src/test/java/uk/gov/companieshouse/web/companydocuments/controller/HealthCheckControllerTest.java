package uk.gov.companieshouse.web.companydocuments.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class HealthCheckControllerTest {

    @InjectMocks
    private HealthCheckController controllerUnderTest;

    @Test
    @DisplayName("Health check confirms health with HTTP 200")
    public void applicationHealthCheckRunsSuccessfully(){
        // When the health endpoint is polled
        final ResponseEntity<Void> response = controllerUnderTest.getHealthCheck();

        // Then the response is HTTP 200
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}
