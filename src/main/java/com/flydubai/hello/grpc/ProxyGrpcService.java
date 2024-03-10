package com.flydubai.hello.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flydubai.hello.HelloRequest;
import com.flydubai.hello.HelloResponse;
import com.flydubai.hello.Proxy;
import com.flydubai.hello.service.GreetingService;
import com.flydubai.hellosoap.HelloSoapResponse;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

/**
 * The Class ProxyGrpcService.
 * 
 * The ProxyGrpcService class is an entrypoint for the proxy service. It
 * retrieves greeting message and, then transforms it in the required format.
 *
 * @author ashwanipratap
 */
@GrpcService
public class ProxyGrpcService implements Proxy {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ProxyGrpcService.class);

	/** The greeting service. */
	@Inject
	GreetingService greetingService;

	/**
	 * Gets the greeting message.
	 *
	 * @param request the request
	 * @return the greeting message
	 */
	@Override
	public Uni<HelloResponse> getGreetingMessage(HelloRequest request) {
		HelloSoapResponse response = greetingService.getMessage(request);
		logger.info("Response: {}", response.getResponse());
		HelloResponse transformedResponse = HelloResponse.newBuilder()
				.setGreetingMessage(response.getResponse().replace("Welcome", "Hello")).build();
		return Uni.createFrom().item(() -> transformedResponse);
	}
}
