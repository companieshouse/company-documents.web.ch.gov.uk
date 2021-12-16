# Companies House Company documents WEB

# NEEDS LOG4J SECURITY FIXES - THIS SERVICE IS NOT IN LIVE OR STAGING AND HAS BEEN REMOVED FROM CI-DEV
# PIPELINE HAS HAD AUTO DEPLOY TO CI-DEV DISABLED
# SEE DEBT-1499 AND DEBT-1498 IN JIRA

## company-documents.web.ch.gov.uk
Web application for handling the retrieval of company documents

### Requirements
* [Java 8][1]
* [Maven][2]
* [Git][3]

### Getting Started
1. Run `make dev` to build
2. Run `./start.sh` to run

### Environment Variables
Name                       | Description                                  | Mandatory | Location
-------------------------- | -------------------------------------------- | --------- | ------------
COMPANY_DOCUMENTS_WEB_PORT | Port this application runs on when deployed. | ✓         | `./start.sh`

### Endpoints
Path                                                                  | Method   | Description
--------------------------------------------------------------------- | -------- | --------------------------------------------------------------------
*`/healthcheck`*                                                      | GET      | Returns HTTP OK (`200`) to indicate a healthy application instance.
*`/company-documents/view-correspondence`*                            | GET/POST | Responsible for the landing page for the service.
*`/company-documents/{companyNumber}/details`*                        | GET/POST | Responsible for returning details of a company to a user.
*`/company-documents/company/{companyNumber}/list-company-documents`* | GET      | Responsible for returning a list of a company's documents to a user.

### Vagrant
At present, in development this project will be run on a vagrant vm. This service is part of the `ubic` group `chs.output`. This service also has a dependence 
on applications hosted in the `chs.core` group.

### Additional Information
This project is **not live**, nor is it ready for production yet. This application was developed for the 'Alternative Paper Output'
project which was de-prioritised and will be resumed in the future.

[1]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[2]: https://maven.apache.org/download.cgi
[3]: https://git-scm.com/downloads

