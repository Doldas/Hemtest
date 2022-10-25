package com.hemtest.utils;

/**
 * String Sanitizer, Sanitizing the string from special chars and normalizing
 * the string by replacing multiple whitespaces into a singular space.
 * 
 * @author anton lekedal (Doldas)
 *
 */
public class StringSanitizer {
	private static final String SPECIALCHARS_FILTER = "[^a-öA-Ö]", WHITESPACE_FILTER = " +", REPLACE_WITH = " ";

	/**
	 * Sanitize and format the text string
	 *
	 * @param text The text to be sanitized
	 * @return A sanitized string
	 */
	public static String sanitizeString(String text) {
		String sanitized = text.replaceAll(SPECIALCHARS_FILTER, REPLACE_WITH).replaceAll("[{}]", REPLACE_WITH).trim();
		sanitized = sanitized.replaceAll(WHITESPACE_FILTER, REPLACE_WITH);
		return sanitized;
	}
}
