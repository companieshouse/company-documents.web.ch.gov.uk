package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Controller
@RequestMapping(value=NavigationUrls.VIEW_CORRESPONDENCE)
public class ViewCorrespondenceController extends BaseController {

    private final String chsUrl;

    public ViewCorrespondenceController(@Value("${chs.url}") String chsUrl) {
        this.chsUrl = chsUrl;
    }

    @GetMapping
    public String getCorrespondence() {
        return getTemplateName();
    }

    @PostMapping
    public String postCorrespondence(RedirectAttributes attributes) {

        attributes.addAttribute("forward", NavigationUrls.COMPANY_DETAILS);
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + chsUrl + "/company-lookup/search";
    }

    @Override
    protected String getTemplateName() {
        return "viewCorrespondence";
    }
}
