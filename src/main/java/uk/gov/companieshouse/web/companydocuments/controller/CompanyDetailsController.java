package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import uk.gov.companieshouse.web.companydocuments.service.CompanyService;

@Controller
@RequestMapping("/company-documents/{companyNumber}/details")
public class CompanyDetailsController extends BaseController {

    @Value("${chs.url}")
    private String chsUrl;

    private CompanyService companyService;

    @Autowired
    public CompanyDetailsController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String getCompanyDetails(@PathVariable String companyNumber,
                                    Model model) {

        model.addAttribute("companyDetail", companyService.getCompanyDetail(companyNumber));

        return getTemplateName();
    }

    @PostMapping
    public String submitCompanyDetails(@PathVariable String companyNumber) {
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + chsUrl + "/company-documents/company/"
                        + companyNumber + "/list-company-documents";
    }

    @Override
    protected String getTemplateName() {
        return "companyDetails";
    }
}
