package uk.gov.companieshouse.web.companydocuments.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnumerationConstantsMapperTest {

    private static final String COMPANY_TYPE_CONSTANTS_KEY = "company_type";

    private static final String COMPANY_TYPE_KEY = "companyTypeKey";

    private static final String COMPANY_TYPE_VALUE = "companyTypeValue";

    private static final String COMPANY_TYPE_OTHER_KEY = "companyTypeOtherKey";

    private static final String COMPANY_STATUS_CONSTANTS_KEY = "company_status";

    private static final String COMPANY_STATUS_KEY = "companyStatusKey";

    private static final String COMPANY_STATUS_VALUE = "companyStatusValue";

    private static final String COMPANY_STATUS_OTHER_KEY = "companyStatusOtherKey";

    private static final String FILE_PATH = "api-enumerations/constants.yml";

    @Mock
    private YamlResourceMapper yamlResourceMapper;

    private EnumerationConstantsMapper enumerationConstantsMapper;

    @BeforeEach
    private void setup() {

        Map<String, String> companyTypes = new HashMap<>();
        companyTypes.put(COMPANY_TYPE_KEY, COMPANY_TYPE_VALUE);

        Map<String, String> companyStatuses = new HashMap<>();
        companyStatuses.put(COMPANY_STATUS_KEY, COMPANY_STATUS_VALUE);

        Map<String, Object> constants = new HashMap<>();
        constants.put(COMPANY_TYPE_CONSTANTS_KEY, companyTypes);
        constants.put(COMPANY_STATUS_CONSTANTS_KEY, companyStatuses);

        when(yamlResourceMapper.fetchYamlFromFile(FILE_PATH)).thenReturn(constants);

        this.enumerationConstantsMapper = new EnumerationConstantsMapper(yamlResourceMapper);
    }

    @Test
    @DisplayName("Get company type")
    void getCompanyType() {

        assertEquals(COMPANY_TYPE_VALUE, enumerationConstantsMapper.getCompanyType(COMPANY_TYPE_KEY));
    }

    @Test
    @DisplayName("Get company type - not found")
    void getCompanyTypeNotFound() {

        assertThrows(IllegalArgumentException.class, () -> enumerationConstantsMapper.getCompanyType(COMPANY_TYPE_OTHER_KEY));
    }

    @Test
    @DisplayName("Get company status")
    void getCompanyStatus() {

        assertEquals(COMPANY_STATUS_VALUE, enumerationConstantsMapper.getCompanyStatus(COMPANY_STATUS_KEY));
    }

    @Test
    @DisplayName("Get company status - not found")
    void getCompanyStatusNotFound() {

        assertThrows(IllegalArgumentException.class, () -> enumerationConstantsMapper.getCompanyStatus(COMPANY_STATUS_OTHER_KEY));
    }
}
