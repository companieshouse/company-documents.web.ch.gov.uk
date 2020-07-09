package uk.gov.companieshouse.web.companydocuments.session.impl;

import java.util.Map;
import org.springframework.stereotype.Component;
import uk.gov.companieshouse.session.handler.SessionHandler;
import uk.gov.companieshouse.web.companydocuments.session.SessionService;

@Component
public class SessionServiceImpl implements SessionService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getSessionDataFromContext() {

        return SessionHandler.getSessionDataFromContext();
    }
}
