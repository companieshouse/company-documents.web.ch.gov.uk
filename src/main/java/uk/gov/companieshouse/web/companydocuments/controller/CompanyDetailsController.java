package uk.gov.companieshouse.web.companydocuments.controller;

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
@RequestMapping(value=NavigationUrls.COMPANY_DETAILS)
public class CompanyDetailsController extends BaseController {

    private final String chsUrl;
    private final CompanyService companyService;

    public CompanyDetailsController(CompanyService companyService,
                    @Value("${chs.url}") String chsUrl) {
        this.companyService = companyService;
        this.chsUrl = chsUrl;
    }

    @GetMapping
    public String getCompanyDetails(@PathVariable String companyNumber,
                                    Model model) {

        model.addAttribute("backButton", NavigationUrls.COMPANY_LOOKUP_FORWARD_TO_COMPANY_DETAILS);
        model.addAttribute("companyDetail", companyService.getCompanyDetail(companyNumber));

        return getTemplateName();
    }

    @PostMapping
    public String submitCompanyDetails(@PathVariable String companyNumber) {
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + chsUrl + NavigationUrls.LIST_COMPANY_DOCUMENTS.replace("{companyNumber}", companyNumber);
    }

    @Override
    protected String getTemplateName() {
        return "companyDetails";
    }
}
