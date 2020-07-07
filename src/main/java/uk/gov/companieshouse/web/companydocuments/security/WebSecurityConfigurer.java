package uk.gov.companieshouse.web.companydocuments.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import uk.gov.companieshouse.auth.filter.CompanyAuthFilter;
import uk.gov.companieshouse.auth.filter.HijackFilter;
import uk.gov.companieshouse.session.handler.SessionHandler;

@EnableWebSecurity
// Ignore Sonar: Utility classes should not have public constructors (java:S1118)
@java.lang.SuppressWarnings("squid:S1118")
public class WebSecurityConfigurer {

    @Configuration
    @Order(1)
    public static class GovUkAccountsSecurityFilterConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/company-documents/company/**")
            .addFilterBefore(new SessionHandler(), BasicAuthenticationFilter.class)
            .addFilterBefore(new CompanyAuthFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new HijackFilter(), BasicAuthenticationFilter.class);
        }
    }

    @Configuration
    public static class WebSecurityFilterConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.addFilterBefore(new SessionHandler(), BasicAuthenticationFilter.class);
            http.addFilterBefore(new HijackFilter(), BasicAuthenticationFilter.class);
        }
    }
}
