package uk.gov.companieshouse.web.companydocuments.util;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnumerationConstantsMapper {

    private final Map<String, String> companyTypes;

    private final Map<String, String> companyStatuses;

    @Autowired
    public EnumerationConstantsMapper(YamlResourceMapper yamlResourceMapper) {

        Map<String, Object> enumerationConstants = yamlResourceMapper.fetchYamlFromFile("api-enumerations/constants.yml");

        this.companyTypes = (Map<String, String>) enumerationConstants.get("company_type");
        this.companyStatuses = (Map<String, String>) enumerationConstants.get("company_status");
    }

    public String getCompanyType(String enumeration) {

        return companyTypes.get(enumeration);
    }

    public String getCompanyStatus(String enumeration) {

        return companyStatuses.get(enumeration);
    }
}
