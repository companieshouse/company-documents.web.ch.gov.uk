package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

    protected BaseController() {
    }

    @ModelAttribute("templateName")
    protected abstract String getTemplateName();
}
