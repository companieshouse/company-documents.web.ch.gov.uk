package uk.gov.companieshouse.web.companydocuments.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.web.companydocuments.CompanyDocumentsWebApplication;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyDocumentsWebApplication.APPLICATION_NAMESPACE);

    @ExceptionHandler(value = { RuntimeException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleRuntimeException(HttpServletRequest request, Exception ex) {

        LOG.errorRequest(request, ex);
        return "error";
    }
}