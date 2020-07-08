package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company-documents/company/{companyNumber}/list-company-documents")
public class ListCompanyDocumentsController extends BaseController {

    @GetMapping
    public String getListCompanyDocu() {
        return getTemplateName();
    }

    @Override
    protected String getTemplateName() {
        return "listCompanyDocuments";
    }
}
