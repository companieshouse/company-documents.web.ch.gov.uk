package uk.gov.companieshouse.web.companydocuments.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HealthCheckControllerIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HealthCheckController controller;

    @BeforeEach
    private void setup() {

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Successfully returns health status")
    public void returnHealthStatusSuccessfully() throws Exception {
        this.mockMvc.perform(get("/company-documents/health-check"))
                .andExpect(status().isOk());
    }
}
