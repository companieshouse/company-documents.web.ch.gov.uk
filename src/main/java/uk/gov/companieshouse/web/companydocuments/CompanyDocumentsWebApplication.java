package uk.gov.companieshouse.web.companydocuments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.gov.companieshouse.web.companydocuments.interceptor.LoggingInterceptor;

@SpringBootApplication
public class CompanyDocumentsWebApplication implements WebMvcConfigurer {

    public static final String APPLICATION_NAMESPACE = "company-documents.web.ch.gov.uk";

    private final LoggingInterceptor loggingInterceptor;

    public CompanyDocumentsWebApplication(LoggingInterceptor loggingInterceptor) {

        this.loggingInterceptor = loggingInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(CompanyDocumentsWebApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loggingInterceptor);
    }
}
