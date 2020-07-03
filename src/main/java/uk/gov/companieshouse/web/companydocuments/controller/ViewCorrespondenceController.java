package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Controller
@RequestMapping("/company-documents/view-correspondence")
public class ViewCorrespondenceController extends BaseController {

    @GetMapping
    public String getCorrespondence() {
        return getTemplateName();
    }

    @PostMapping
    public String postCorrespondence(RedirectAttributes attributes) {

        attributes.addAttribute("forward", "/company-documents/{companyNumber}/details");
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/company-lookup/search";
    }

    @Override
    protected String getTemplateName() {
        return "viewCorrespondence";
    }
}
