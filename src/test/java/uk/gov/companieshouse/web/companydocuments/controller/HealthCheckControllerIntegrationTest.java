package uk.gov.companieshouse.web.companydocuments.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HealthCheckControllerIntegrationTest {

    private MockMvc mockMvc;

    @BeforeEach
    private void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(new HealthCheckController()).build();
    }

    @Test
    @DisplayName("Successfully returns health status")
    void returnHealthStatusSuccessfully() throws Exception {
        mockMvc.perform(get("/company-documents/healthcheck"))
                .andExpect(status().isOk());
    }
}
