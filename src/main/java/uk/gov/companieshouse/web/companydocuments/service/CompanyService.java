package uk.gov.companieshouse.web.companydocuments.service;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;
import uk.gov.companieshouse.api.error.ApiErrorResponseException;
import uk.gov.companieshouse.api.handler.exception.URIValidationException;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.web.companydocuments.api.ApiClientService;
import uk.gov.companieshouse.web.companydocuments.exception.ServiceException;
import uk.gov.companieshouse.web.companydocuments.model.CompanyDetail;
import uk.gov.companieshouse.web.companydocuments.transformer.CompanyTransformer;

@Service
public class CompanyService {

    private static final UriTemplate COMPANY_URI = new UriTemplate("/company/{companyNumber}");

    private final ApiClientService apiClientService;

    private final CompanyTransformer companyTransformer;

    public CompanyService(ApiClientService apiClientService, CompanyTransformer companyTransformer) {
        this.apiClientService = apiClientService;
        this.companyTransformer = companyTransformer;
    }

    public CompanyDetail getCompanyDetail(String companyNumber) {

        try {
            CompanyProfileApi companyProfile =
                    apiClientService.getApiClient().company()
                            .get(COMPANY_URI.expand(companyNumber).toString())
                                    .execute().getData();

            return companyTransformer.getCompanyDetail(companyProfile);

        } catch (ApiErrorResponseException e) {

            throw new ServiceException("Error encountered when fetching company profile", e);

        } catch (URIValidationException e) {

            throw new ServiceException("Invalid URI for company resource", e);
        }
    }
}
