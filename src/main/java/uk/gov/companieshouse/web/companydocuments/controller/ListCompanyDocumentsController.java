package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriTemplate;

@Controller
@RequestMapping(value=NavigationUrls.LIST_COMPANY_DOCUMENTS)
public class ListCompanyDocumentsController extends BaseController {

    @GetMapping
    public String getListCompanyDocuments(@PathVariable String companyNumber, Model model) {

        model.addAttribute(BACK_BUTTON_KEY, new UriTemplate(NavigationUrls.COMPANY_DETAILS).expand(companyNumber));
        return getTemplateName();
    }

    @Override
    protected String getTemplateName() {
        return "listCompanyDocuments";
    }
}
