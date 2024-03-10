package com.flydubai.hello.client;

import com.flydubai.hellosoap.HelloSoap;
import com.flydubai.hellosoap.HelloSoapPortType;
import com.flydubai.hellosoap.HelloSoapResponse;

import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * This class represents a client for the Hello SOAP service.
 *
 * @author ashwanipratap
 */
@ApplicationScoped
public class HelloSoapClient {

	/** The hello soap port type service. */
	@CXFClient("helloSoap")
	HelloSoapPortType helloSoapPortTypeService;

	/**
	 * Retrieve response from remote soap service
	 *
	 * @param clientName the client name
	 * @return the hello soap response
	 */
	public HelloSoapResponse fetchResponse(String clientName) {
		HelloSoap helloSoapRequest = new HelloSoap();
		helloSoapRequest.setClientName(clientName);
		return helloSoapPortTypeService.hello(helloSoapRequest);
	}
}
