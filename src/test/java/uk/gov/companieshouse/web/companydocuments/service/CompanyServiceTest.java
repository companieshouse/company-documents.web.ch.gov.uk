package uk.gov.companieshouse.web.companydocuments.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.companieshouse.api.ApiClient;
import uk.gov.companieshouse.api.error.ApiErrorResponseException;
import uk.gov.companieshouse.api.handler.company.CompanyResourceHandler;
import uk.gov.companieshouse.api.handler.company.request.CompanyGet;
import uk.gov.companieshouse.api.handler.exception.URIValidationException;
import uk.gov.companieshouse.api.model.ApiResponse;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.web.companydocuments.api.ApiClientService;
import uk.gov.companieshouse.web.companydocuments.exception.ServiceException;
import uk.gov.companieshouse.web.companydocuments.model.CompanyDetail;
import uk.gov.companieshouse.web.companydocuments.transformer.CompanyTransformer;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyServiceTest {

    private static final String COMPANY_NUMBER = "companyNumber";

    private static final String COMPANY_URI = "/company/" + COMPANY_NUMBER;

    @Mock
    private ApiClient apiClient;

    @Mock
    private ApiClientService apiClientService;

    @Mock
    private CompanyResourceHandler companyResourceHandler;

    @Mock
    private CompanyGet companyGet;

    @Mock
    private ApiResponse<CompanyProfileApi> responseWithData;

    @Mock
    private CompanyProfileApi companyProfile;

    @Mock
    private CompanyDetail companyDetail;

    @Mock
    private CompanyTransformer companyTransformer;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    private void setup() {

        when(apiClientService.getApiClient()).thenReturn(apiClient);

        when(apiClient.company()).thenReturn(companyResourceHandler);

        when(companyResourceHandler.get(COMPANY_URI)).thenReturn(companyGet);
    }

    @Test
    @DisplayName("Get Company Detail - Success Path")
    void getCompanyDetailSuccess() throws ApiErrorResponseException, URIValidationException {

        when(companyGet.execute()).thenReturn(responseWithData);

        when(responseWithData.getData()).thenReturn(companyProfile);

        when(companyTransformer.getCompanyDetail(companyProfile)).thenReturn(companyDetail);

        CompanyDetail returned = companyService.getCompanyDetail(COMPANY_NUMBER);

        assertEquals(companyDetail, returned);
    }

    @Test
    @DisplayName("Get Company Detail - Throws ApiErrorResponseException")
    void getCompanyDetailThrowsApiErrorResponseException() throws ApiErrorResponseException, URIValidationException {

        when(companyGet.execute()).thenThrow(ApiErrorResponseException.class);

        assertThrows(ServiceException.class, () ->
                companyService.getCompanyDetail(COMPANY_NUMBER));
    }

    @Test
    @DisplayName("Get Company Detail - Throws URIValidationException")
    void getCompanyDetailThrowsURIValidationException() throws ApiErrorResponseException, URIValidationException {

        when(companyGet.execute()).thenThrow(URIValidationException.class);

        assertThrows(ServiceException.class, () ->
                companyService.getCompanyDetail(COMPANY_NUMBER));
    }
}
