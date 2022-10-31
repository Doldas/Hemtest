package com.hemtest.webserver.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hemtest.utils.StringSanitizer;
import com.hemtest.webserver.services.WordOccurrencySerivce;
import com.hemtest.webserver.utils.IPAddressUtil;

import io.github.resilience4j.retry.annotation.Retry;

/**
 * The Text Analyzer Web API Controller
 *
 * @author anton lekedal (Doldas)
 *
 */
@RestController
public class TextAnalyzerController {
	private static final Logger logger = LoggerFactory.getLogger(TextAnalyzerController.class);
	@Autowired
	private WordOccurrencySerivce service;
	@Value("${words.counter.max-characters:5000}")
	private Integer MAX_CHARS; // amount of chars to be inputed

	/**
	 * Counts the frequency for each word in the text
	 *
	 * @return a JSON string containing the 10 most frequent words from the text
	 */
	@Retry(name = "countwords")
	@CacheEvict(value = "count", allEntries = true, key = "#text")
	@PostMapping(path = "count", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> countWords(@RequestBody String text,
			@RequestParam(value = "limit", required = false) Integer limit, HttpServletRequest request) {
		if (text.length() > MAX_CHARS) {
			String excededMSG = "Exceded the max limit by " + (text.length() - MAX_CHARS);
			String message = "A user from ip-address " + IPAddressUtil.getRequestIP(request) + " " + excededMSG;
			logger.warn(message);
			return new ResponseEntity<>(
					"{" + "message: \"Failed to calculate amount of words: Maximum characters is set to " + MAX_CHARS
							+ "," + excededMSG + " \" }",
					HttpStatus.BAD_REQUEST);
		} else if (limit != null && limit > 0)
			return new ResponseEntity<>("{" + service.execute(StringSanitizer.sanitizeString(text), limit).join() + "}",
					HttpStatus.OK);

		return new ResponseEntity<>("{" + service.execute(StringSanitizer.sanitizeString(text)).join() + "}",
				HttpStatus.OK);
	}

}
