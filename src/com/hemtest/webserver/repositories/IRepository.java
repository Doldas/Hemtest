package com.hemtest.webserver.repositories;

import java.util.stream.Stream;
/**
 * Interface for repositories
 *
 * @author anton lekedal (Doldas)
 *
 */
public interface IRepository<T> {
	/**
	 * Create new object
	 * @param newObj
	 */
	public void create(T newObj);
	/**
	 * convert a stream of objects into a string representation
	 * @param objects
	 * @return string representation that contains a list of objects
	 */
	public default String toString(Stream<T> objects) {
		StringBuilder sb = new StringBuilder();
		objects.forEach(obj -> {
			sb.append(obj.toString() + ",");
		});

		return sb.toString().substring(0, sb.toString().length() - 1);
	}
}
