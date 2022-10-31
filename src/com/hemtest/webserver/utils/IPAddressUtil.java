package com.hemtest.webserver.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * A utility class for fetching the IP address sent by the client.
 * 
 * @author anton lekedal (Doldas)
 *
 */
public class IPAddressUtil {
	private static final String[] IP_HEADERS = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	/**
	 * Fetch the Request IP Address
	 * 
	 * @param request
	 * @return the IP address from the request.
	 */
	public static String getRequestIP(HttpServletRequest request) {
		for (String header : IP_HEADERS) {
			String value = request.getHeader(header);
			if (value == null || value.isEmpty())
				continue;
			return value.split("\\s*,\\s*")[0];
		}
		return request.getRemoteAddr();
	}
}
