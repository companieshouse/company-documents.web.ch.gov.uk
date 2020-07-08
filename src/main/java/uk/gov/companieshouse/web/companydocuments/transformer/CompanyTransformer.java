package uk.gov.companieshouse.web.companydocuments.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.api.model.company.RegisteredOfficeAddressApi;
import uk.gov.companieshouse.web.companydocuments.model.CompanyDetail;
import uk.gov.companieshouse.web.companydocuments.util.EnumerationConstantsMapper;

@Component
public class CompanyTransformer {

    private final EnumerationConstantsMapper enumerationConstantsMapper;

    @Autowired
    public CompanyTransformer(EnumerationConstantsMapper enumerationConstantsMapper) {
        this.enumerationConstantsMapper = enumerationConstantsMapper;
    }

    public CompanyDetail getCompanyDetail(CompanyProfileApi companyProfile) {

        CompanyDetail companyDetail = new CompanyDetail();

        companyDetail.setCompanyName(companyProfile.getCompanyName());
        companyDetail.setCompanyNumber(companyProfile.getCompanyNumber());

        RegisteredOfficeAddressApi registeredOfficeAddress = companyProfile
                .getRegisteredOfficeAddress();

        if (registeredOfficeAddress != null) {

            companyDetail.setRegisteredOfficeAddress(
                    ((registeredOfficeAddress.getAddressLine1() == null) ? "" : registeredOfficeAddress.getAddressLine1()) +
                    ((registeredOfficeAddress.getAddressLine2() == null) ? "" : ", " + registeredOfficeAddress.getAddressLine2() ) +
                    ((registeredOfficeAddress.getPostalCode() == null) ? "" : ", " + registeredOfficeAddress.getPostalCode()));
        }

        companyDetail.setStatus(enumerationConstantsMapper.getCompanyStatus(companyProfile.getCompanyStatus()));
        companyDetail.setCompanyType(enumerationConstantsMapper.getCompanyType(companyProfile.getType()));
        companyDetail.setIncorporationDate(companyProfile.getDateOfCreation());

        return companyDetail;
    }
}
