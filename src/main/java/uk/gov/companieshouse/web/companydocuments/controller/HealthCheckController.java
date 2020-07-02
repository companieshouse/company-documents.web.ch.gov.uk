package uk.gov.companieshouse.web.companydocuments.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Returns HTTP OK response to indicate a healthy service is running
 */
@Controller
@RequestMapping("/company-documents/healthcheck")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<Void> getHealthCheck (){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
