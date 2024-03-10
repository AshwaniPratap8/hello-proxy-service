package com.flydubai.hello.service;

import com.flydubai.hello.HelloRequest;
import com.flydubai.hellosoap.HelloSoapResponse;

/**
 * The Interface GreetingService.
 */
public interface GreetingService {

	/**
	 * Gets the message.
	 *
	 * @param request the request
	 * @return the message
	 */
	HelloSoapResponse getMessage(HelloRequest request);
}
