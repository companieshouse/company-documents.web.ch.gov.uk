package uk.gov.companieshouse.web.companydocuments.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import uk.gov.companieshouse.web.companydocuments.model.CompanyDetail;
import uk.gov.companieshouse.web.companydocuments.service.CompanyService;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyDetailsControllerTest {

    private static final String COMPANY_NUMBER = "companyNumber";

    private static final String COMPANY_DETAILS_PATH = "/company-documents/" + COMPANY_NUMBER + "/details";

    private static final String COMPANY_DETAILS_VIEW = "companyDetails";

    private static final String COMPANY_DETAILS_MODEL_ATTR = "companyDetail";

    private static final String LIST_COMPANY_DOCUMENTS_PATH = "/company-documents/company/" + COMPANY_NUMBER + "/list-company-documents";

    private static final String CHS_URL = "localhost:888";

    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @Mock
    private CompanyDetail companyDetail;

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CompanyDetailsController(companyService, CHS_URL)).build();
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

    @Test
    @DisplayName("Confirm company details")
    void confirmCompanyDetails() throws Exception {

        mockMvc.perform(post(COMPANY_DETAILS_PATH))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(UrlBasedViewResolver.REDIRECT_URL_PREFIX + CHS_URL + LIST_COMPANY_DOCUMENTS_PATH));
    }
}
