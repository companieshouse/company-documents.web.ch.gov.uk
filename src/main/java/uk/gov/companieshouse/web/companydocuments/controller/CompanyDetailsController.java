package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.gov.companieshouse.web.companydocuments.service.CompanyService;

@Controller
@RequestMapping("/company-documents/{companyNumber}/details")
public class CompanyDetailsController extends BaseController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public String getCompanyDetails(@PathVariable String companyNumber,
                                    Model model) {

        model.addAttribute("companyDetail", companyService.getCompanyDetail(companyNumber));

        return getTemplateName();
    }

    @PostMapping
    public void submitCompanyDetails() {

    }

    @Override
    protected String getTemplateName() {
        return "companyDetails";
    }
}
