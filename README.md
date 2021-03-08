# apigee-OAuth-SAML
Apigee OAuth with SAML IDP integration

In this implementation Apigee acts as an authorization server issuing client credentials and tokens. Integrate with any SAML based IDP to perform user authentication. 

This achieves the same objective as [Using SAML Assertions as Authorization Grants](https://tools.ietf.org/html/rfc7522#section-2.1) however the OAuth interaction and the grant_type are different.

## Install
 * Create KVM
     - KVMGetSAMLConfig
         + issuer
         + destination
         + domain
 * Create Cache resource
     - SAMLState
 * Create Truststore
     - SAMLTrust with certificate from SAML IDP 
 * Update PLATFORM_SAMPLES_INSTALLDIR in java/build.sh 
 * Build java file and create apiproxy/resources/java/EncodeSAML.jar
 * Deploy proxy
 * Create API product, App and get client credentials
 * Invocation
     - Authz code
         + Using a browser - https://org-env.apigee.net/oauth20/v1/authorize?client_id=cliendid&state=99999&response_type=code&redirect_uri=https%3A%2F%2Fcode.example.com
         + In a UNIX based terminal
         ```
              curl -X POST 'https://org-env.apigee.net/oauth20/v1/token' \
                -u 'clientid:clientsecret' \
                -H 'Content-Type: application/x-www-form-urlencoded' \
                -d 'grant_type=authorization_code&code=authzcode&redirect_uri=https%3A%2F%2Fcode.example.com'
         ```
     - Implicit
         + Using a browser - https://org-env.apigee.net/oauth20/v1/authorize?client_id=cliendid&state=99999&response_type=token&redirect_uri=https%3A%2F%2Fcode.example.com

## Cleanup
 * Delete App
 * Delete API Product
 * Undeploy and delete API proxy
 * Delete Cache - SAMLState
 * Delete KVM - KVMGetSAMLConfig
 * Delete Truststore - SAMLTrust

## License

This code is Copyright (c) 2020-2021 Google LLC, and is released under the
Apache Source License v2.0. For information see the [LICENSE](LICENSE) file.

## Disclaimer

This example is not an official Google product, nor is it part of an official Google product. This implementation is not thoroughly tested.

## Author

Madhan Sadasivam

