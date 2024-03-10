package com.flydubai.hello.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flydubai.hello.HelloRequest;
import com.flydubai.hello.client.HelloSoapClient;
import com.flydubai.hello.service.GreetingService;
import com.flydubai.hellosoap.HelloSoapResponse;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * The Class GreetingServiceImpl.
 * 
 * The service implementation class that handles communication with remote SOAP
 * service.
 *
 * @author ashwanipratap
 */
@ApplicationScoped
public class GreetingServiceImpl implements GreetingService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

	/** The Constant FORMAT_ERROR_MESSAGE. */
	private static final String FORMAT_ERROR_MESSAGE = "Error Message: %s \\nStatusCode: %s ";

	/** The hello soap client. */
	private @Inject HelloSoapClient helloSoapClient;

	/**
	 * Gets the message.
	 *
	 * @param request the request
	 * @return the message
	 */
	@Override
	public HelloSoapResponse getMessage(HelloRequest request) {
		try {
			return helloSoapClient.fetchResponse(request.getClientName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error: ", e.getLocalizedMessage());
			throw new StatusRuntimeException(Status
					.fromCode(Status.Code.INTERNAL)
					.withDescription(FORMAT_ERROR_MESSAGE.format(e.getMessage())));
		}
	}
}
