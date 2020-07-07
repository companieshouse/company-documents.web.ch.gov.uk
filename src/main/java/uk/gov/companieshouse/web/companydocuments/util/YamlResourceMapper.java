package uk.gov.companieshouse.web.companydocuments.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public class YamlResourceMapper {

    public Map<String, Object> fetchYamlFromFile(String filePath) {

        try {
            return new Yaml().load(
                    new FileInputStream(new File(filePath)));

        } catch (FileNotFoundException e) {

            throw new IllegalArgumentException("Could not load YAML file: " + filePath, e);
        }
    }
}
