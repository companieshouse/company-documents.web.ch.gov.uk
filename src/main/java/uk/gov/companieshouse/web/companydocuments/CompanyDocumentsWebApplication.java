package uk.gov.companieshouse.web.companydocuments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyDocumentsWebApplication {

    public static final String APPLICATION_NAMESPACE = "company-documents.web.ch.gov.uk";

    public static void main(String[] args) {
        SpringApplication.run(CompanyDocumentsWebApplication.class, args);
    }
}
