# docu-connector
## Introduction

This project contains a module that extends the Mule 4 Runtime to simplify JWT-based authentication with the DocuSign e-signature service.
It contains a single Mule operation which, when included in a flow, makes it easy to obtain the OAuth token which can then be used in HTTPS requests to the DocuSign service.

## Installation

Download the release JAR file and load it into your Maven repository.
Once present, you can add a dependency to your pom.xml to incorporate the module:

> <dependency\>
> <groupid\>uk.org.mule.docu\</groupid\>
> <artifactid\>docu-connector\</artifactid\>
> <version\>0.1.0\</version\>
> <classifier\>mule-plugin\</classifier\>
> </dependency\>
> 
## Provide Consent

Before you can use the connector, you will need to provide consent for it to impersonate your DocuSign user, click on the link below to do so:

> [Provide Consent](https://account-d.docusign.com/oauth/auth?response_type=code&scope=signature%20impersonation&client_id=480bf239-9265-4f94-a333-5b1eebde0300&redirect_uri=https://www.mule.org.uk/docu-connector/)
> 
## Usage
The "Get user access token" operation can be placed into your flow like any other component, and requires that a configuration is associated that specifies:

+ The Base Path for the OAuth token request, which should be either "account-d.docusign.com" or "account.docusign.com"
+ The guid version of the DocuSign user that you wish to have the docu-connector impersonate

![DocuSign Config](/images/docusign-config.png)

If you wish to, the access token returned can be assigned to a target variable of your choosing:

![Get user access token target](/images/get-user-access-token-target.png)

Then, the access token obtained can be incorporated into the "Authorization" header of any HTTPS requests to the DocuSign service:

![HTTP Authorization Header](/images/http-authorized-request.png)
