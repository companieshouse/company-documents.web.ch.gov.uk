package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

    protected static final String BACK_BUTTON_KEY = "backButton";
    
    protected BaseController() {
    }

    @ModelAttribute("templateName")
    protected abstract String getTemplateName();
}
