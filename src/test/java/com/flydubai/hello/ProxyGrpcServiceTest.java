package com.flydubai.hello;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;

/**
 * The Class ProxyGrpcServiceTest.
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProxyGrpcServiceTest {

	/** The stub. */
	@GrpcClient
	MutinyProxyGrpc.MutinyProxyStub stub;

	/**
	 * Test greeting message with valid client name.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGreetingMessageWithValidClientName() throws Exception {
		CompletableFuture<HelloResponse> future = new CompletableFuture<>();
		stub.getGreetingMessage(HelloRequest.newBuilder().setClientName("Test Client").build()).subscribe()
				.with(res -> future.complete(res));

		String actualMessage = future.get().getGreetingMessage();
		Assertions.assertEquals("Hello Test Client", actualMessage);
	}

	/**
	 * Test greeting message with empty client name.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGreetingMessageWithEmptyClientName() throws Exception {
		CompletableFuture<HelloResponse> future = new CompletableFuture<>();
		stub.getGreetingMessage(HelloRequest.newBuilder().setClientName("").build()).subscribe()
				.with(res -> future.complete(res), error -> future.completeExceptionally(error));

		Assertions.assertThrows(StatusRuntimeException.class, () -> {
			try {
				future.get();
			} catch (ExecutionException e) {
				throw e.getCause();
			}
		});
	}
}