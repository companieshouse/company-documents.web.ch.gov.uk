package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/correspondence")
public class CorrespondencePageController extends BaseController {

    @GetMapping
    public String getCorrespondence() {
        return getTemplateName();
    }

    @PostMapping
    public void postCorrespondence() {
    }

    @Override
    protected String getTemplateName() {
        return "correspondence";
    }
}
