package uk.gov.companieshouse.web.companydocuments.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.web.servlet.view.UrlBasedViewResolver.REDIRECT_URL_PREFIX;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {
        "chs.url=testValue",
    })
public class ViewCorrespondenceControllerTest {

    @Value("${chs.url}")
    private static String chsUrl;

    private static final String VIEW_CORRESPONDENCE_PATH = "/company-documents/view-correspondence";
    private static final String VIEW_CORRESPONDENCE_FORWARD_SUCCESS_VIEW = REDIRECT_URL_PREFIX + chsUrl + "/company-lookup/search";

    private MockMvc mockMvc;

    @InjectMocks
    private ViewCorrespondenceController controller;

    @BeforeEach
    private void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Get View Correspondence - Successful")
    void getCorrespondenceSuccessfully() throws Exception {
        mockMvc.perform(get(VIEW_CORRESPONDENCE_PATH))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Post View Correspondence - redirect to Company Lookup")
    void postRequestCriteriaMet() throws Exception {

        mockMvc.perform(post(VIEW_CORRESPONDENCE_PATH))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(VIEW_CORRESPONDENCE_FORWARD_SUCCESS_VIEW));
    }
}
