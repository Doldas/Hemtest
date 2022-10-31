package com.hemtest.webserver.services;

import java.util.concurrent.CompletableFuture;

/**
 * The interface for Services
 *
 * @author anton lekedal (Doldas)
 *
 */
public interface IService<T, E> {
	/**
	 * Executing the service request
	 * 
	 * @param data to be handled
	 * @return A response back after the execution
	 */
	public CompletableFuture<String> execute(T data);
}
