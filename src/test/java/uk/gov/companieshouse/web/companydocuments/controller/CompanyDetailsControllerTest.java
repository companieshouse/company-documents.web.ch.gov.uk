package uk.gov.companieshouse.web.companydocuments.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.gov.companieshouse.web.companydocuments.model.CompanyDetail;
import uk.gov.companieshouse.web.companydocuments.service.CompanyService;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyDetailsControllerTest {

    private static final String COMPANY_NUMBER = "companyNumber";

    private static final String COMPANY_DETAILS_PATH = "/company-documents/" + COMPANY_NUMBER + "/details";

    private static final String COMPANY_DETAILS_VIEW = "companyDetails";

    private static final String COMPANY_DETAILS_MODEL_ATTR = "companyDetail";

    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @Mock
    private CompanyDetail companyDetail;

    private CompanyDetailsController controller;

    @BeforeEach
    private void setup() {
        this.controller = new CompanyDetailsController(companyService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Get company details")
    void getCompanyDetails() throws Exception {

        when(companyService.getCompanyDetail(COMPANY_NUMBER)).thenReturn(companyDetail);

        mockMvc.perform(get(COMPANY_DETAILS_PATH))
                .andExpect(status().isOk())
                .andExpect(view().name(COMPANY_DETAILS_VIEW))
                .andExpect(model().attributeExists(COMPANY_DETAILS_MODEL_ATTR));
    }


}
