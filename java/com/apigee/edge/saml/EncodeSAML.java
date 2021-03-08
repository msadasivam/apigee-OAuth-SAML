package com.apigee.edge.saml;

import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.message.MessageContext;


import java.util.*;
import java.util.zip.*;
import java.lang.*;
import java.io.*;
import java.net.URLEncoder;


public class EncodeSAML implements Execution {

	public ExecutionResult execute(MessageContext messageContext, ExecutionContext executionContext) {
		
		try {

			String samlDestination = messageContext.getVariable("samlinfo.destination");
			String ourDomain = messageContext.getVariable("samlinfo.ourdomain");
			String ID = messageContext.getVariable("samlinfo.id");
			String date = messageContext.getVariable("samlinfo.IssueInstant");


			String saml = String.format("<samlp:AuthnRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" Destination=\"%s\" ID=\"%s\" IssueInstant=\"%s\" ProtocolBinding=\"urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST\" Version=\"2.0\"><saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">%s</saml:Issuer></samlp:AuthnRequest>", samlDestination, ID, date, ourDomain);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
        	Deflater deflater = new Deflater( Deflater.DEFAULT_COMPRESSION, true );
	        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(os, deflater);
    	    deflaterOutputStream.write( saml.getBytes( "UTF-8" ) );
    	    deflaterOutputStream.close();
    	    os.close();
    	    String base64 = Base64.getEncoder().encodeToString( os.toByteArray());	

			messageContext.setVariable("samlinfo.deswithurl", 
					samlDestination + 
					"?SAMLRequest=" + URLEncoder.encode(base64, "UTF-8") + 
					"&RelayState=");
            
            return ExecutionResult.SUCCESS;

		} catch (Exception e) {
			return ExecutionResult.ABORT;
		}
	}
}
