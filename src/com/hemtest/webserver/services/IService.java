package com.hemtest.webserver.services;
/**
 * The interface for Services
 *
 * @author anton lekedal (Doldas)
 *
 */
public interface IService<T, E> {
	/**
	 * Executing the service request
	 * @param data to be handled
	 * @return A response back after the execution
	 */
	public E execute(T data);
}
