package uk.gov.companieshouse.web.companydocuments.controller;

public final class NavigationUrls {
    public static final String VIEW_CORRESPONDENCE = "/company-documents/view-correspondence";
    public static final String COMPANY_LOOKUP_FORWARD_TO_COMPANY_DETAILS = "/company-lookup/search?forward=%2Fcompany-documents%2F%7BcompanyNumber%7D%2Fdetails";
    public static final String COMPANY_DETAILS = "/company-documents/{companyNumber}/details";
    public static final String LIST_COMPANY_DOCUMENTS = "/company-documents/company/{companyNumber}/list-company-documents";
    
    private NavigationUrls(){}
}
