package uk.gov.companieshouse.web.companydocuments.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.companieshouse.api.model.company.CompanyProfileApi;
import uk.gov.companieshouse.api.model.company.RegisteredOfficeAddressApi;
import uk.gov.companieshouse.web.companydocuments.model.CompanyDetail;
import uk.gov.companieshouse.web.companydocuments.util.EnumerationConstantsMapper;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyTransformerTest {

    private static final String COMPANY_NUMBER = "companyNumber";

    private static final String COMPANY_NAME = "companyName";

    private static final String COMPANY_TYPE_ENUM = "companyTypeEnum";

    private static final String COMPANY_TYPE = "companyType";

    private static final String COMPANY_STATUS_ENUM = "companyStatusEnum";

    private static final String COMPANY_STATUS = "companyStatus";

    private static final String ADDRESS_LINE_1 = "addressLine1";

    private static final String ADDRESS_LINE_2 = "addressLine2";

    private static final String POSTCODE = "postcode";

    private static final String FORMATTED_ROA = ADDRESS_LINE_1 + ", " + ADDRESS_LINE_2 + ", " + POSTCODE;

    private static final LocalDate INCORPORATION_DATE = LocalDate.of(2020, 1, 1);

    @Mock
    private EnumerationConstantsMapper enumerationConstantsMapper;

    @InjectMocks
    private CompanyTransformer companyTransformer;

    @Mock
    private CompanyProfileApi companyProfile;

    @Mock
    private RegisteredOfficeAddressApi registeredOfficeAddress;

    @BeforeEach
    private void setup() {

        when(companyProfile.getCompanyNumber()).thenReturn(COMPANY_NUMBER);

        when(companyProfile.getCompanyName()).thenReturn(COMPANY_NAME);

        when(companyProfile.getType()).thenReturn(COMPANY_TYPE_ENUM);

        when(enumerationConstantsMapper.getCompanyType(COMPANY_TYPE_ENUM))
                .thenReturn(COMPANY_TYPE);

        when(companyProfile.getCompanyStatus()).thenReturn(COMPANY_STATUS_ENUM);

        when(enumerationConstantsMapper.getCompanyStatus(COMPANY_STATUS_ENUM))
                .thenReturn(COMPANY_STATUS);

        when(companyProfile.getDateOfCreation()).thenReturn(INCORPORATION_DATE);
    }

    @Test
    @DisplayName("Get company detail - no ROA")
    void getCompanyDetailNoROA() {

        when(companyProfile.getRegisteredOfficeAddress()).thenReturn(null);

        CompanyDetail companyDetail = companyTransformer.getCompanyDetail(companyProfile);

        assertNotNull(companyDetail);
        assertEquals(COMPANY_NUMBER, companyDetail.getCompanyNumber());
        assertEquals(COMPANY_NAME, companyDetail.getCompanyName());
        assertNull(companyDetail.getRegisteredOfficeAddress());
        assertEquals(COMPANY_TYPE, companyDetail.getCompanyType());
        assertEquals(COMPANY_STATUS, companyDetail.getStatus());
        assertEquals(INCORPORATION_DATE, companyDetail.getIncorporationDate());
    }

    @Test
    @DisplayName("Get company detail - with ROA")
    void getCompanyDetailWithROA() {

        when(companyProfile.getRegisteredOfficeAddress()).thenReturn(registeredOfficeAddress);

        when(registeredOfficeAddress.getAddressLine1()).thenReturn(ADDRESS_LINE_1);
        when(registeredOfficeAddress.getAddressLine2()).thenReturn(ADDRESS_LINE_2);
        when(registeredOfficeAddress.getPostalCode()).thenReturn(POSTCODE);

        CompanyDetail companyDetail = companyTransformer.getCompanyDetail(companyProfile);

        assertNotNull(companyDetail);
        assertEquals(COMPANY_NUMBER, companyDetail.getCompanyNumber());
        assertEquals(COMPANY_NAME, companyDetail.getCompanyName());
        assertEquals(FORMATTED_ROA, companyDetail.getRegisteredOfficeAddress());
        assertEquals(COMPANY_TYPE, companyDetail.getCompanyType());
        assertEquals(COMPANY_STATUS, companyDetail.getStatus());
        assertEquals(INCORPORATION_DATE, companyDetail.getIncorporationDate());
    }
}
