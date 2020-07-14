package uk.gov.companieshouse.web.companydocuments.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListCompanyDocumentsControllerTest {

    private static final String COMPANY_NUMBER = "companyNumber";

    private static final String LIST_COMPANY_DOCUMENTS_PATH = "/company-documents/company/" + COMPANY_NUMBER + "/list-company-documents";

    private static final String LIST_COMPANY_DOCUMENTS_VIEW = "listCompanyDocuments";

    private static final String BACK_BUTTON_MODEL_ATTR = "backButton";

    private MockMvc mockMvc;

    @BeforeEach
    private void setup() {

        this.mockMvc = MockMvcBuilders.standaloneSetup(new ListCompanyDocumentsController()).build();
    }

    @Test
    @DisplayName("List company documents")
    void listCompanyDocuments() throws Exception {

        mockMvc.perform(get(LIST_COMPANY_DOCUMENTS_PATH))
                .andExpect(status().isOk())
                .andExpect(view().name(LIST_COMPANY_DOCUMENTS_VIEW))
                .andExpect(model().attributeExists(BACK_BUTTON_MODEL_ATTR));
    }
}
