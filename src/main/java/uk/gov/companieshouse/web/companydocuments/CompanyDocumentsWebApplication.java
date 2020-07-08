package uk.gov.companieshouse.web.companydocuments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.gov.companieshouse.web.companydocuments.interceptor.LoggingInterceptor;
import uk.gov.companieshouse.web.companydocuments.interceptor.UserDetailsInterceptor;

@SpringBootApplication
public class CompanyDocumentsWebApplication implements WebMvcConfigurer {

    public static final String APPLICATION_NAMESPACE = "company-documents.web.ch.gov.uk";

    private final LoggingInterceptor loggingInterceptor;
    private final UserDetailsInterceptor userDetailsInterceptor;

    public CompanyDocumentsWebApplication(LoggingInterceptor loggingInterceptor,
            UserDetailsInterceptor userDetailsInterceptor) {

        this.loggingInterceptor = loggingInterceptor;
        this.userDetailsInterceptor = userDetailsInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(CompanyDocumentsWebApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(userDetailsInterceptor);
    }
}
