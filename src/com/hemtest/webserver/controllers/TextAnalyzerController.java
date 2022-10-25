package com.hemtest.webserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * The Text Analyzer Web API Controller
 *
 * @author anton lekedal (Doldas)
 *
 */
@RestController
public class TextAnalyzerController {
	@Autowired
	private WordOccurrencySerivce service;

	/**
	 * Counts the frequency for each word in the text
	 *
	 * @return a JSON string containing the 10 most frequent words from the text
	 */
	@CacheEvict(value="count", allEntries=true, key="#text")
	@PostMapping(path = "count",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> countWords(@RequestBody String text,@RequestParam(value = "limit", required=false) Integer limit) {
		if(limit!=null && limit > 0)
			return new ResponseEntity<>("{" + service.execute(StringSanitizer.sanitizeString(text),limit) + "}", HttpStatus.OK);

		return new ResponseEntity<>("{" + service.execute(StringSanitizer.sanitizeString(text)) + "}", HttpStatus.OK);
	}
}
