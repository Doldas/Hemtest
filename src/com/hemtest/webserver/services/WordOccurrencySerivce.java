package com.hemtest.webserver.services;

import org.springframework.stereotype.Service;

import com.hemtest.webserver.models.WordOccurrence;
import com.hemtest.webserver.repositories.WordOccurrencyRepository;

/**
 * The Word Occurrence Service
 *
 * @author anton lekedal (Doldas)
 *
 */
@Service
public class WordOccurrencySerivce implements IService<String, String> {
	private final String DELIMITERS = "[ ]";
	private final Integer LIMIT = 10; // amount of words to be shown

	@Override
	public String execute(String data) {
		return execute(data,LIMIT);
	}
	
	/**
	 * Executes the service with an additional parameter
	 * @param data the data to be processed
	 * @param limit The amount of words to be returned
	 * @return a sorted list of word occurrences limited by limit
	 */
	public String execute(String data,Integer limit) {
		WordOccurrencyRepository repo = new WordOccurrencyRepository();
		for (String word : data.split(DELIMITERS)) {
			if (word.length() > 1)
				word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
			else if (word.length() == 1)
				word = word.substring(0, 1).toUpperCase();
			repo.create(new WordOccurrence(word));
		}
		return repo.toString(repo.getSortedStreamByFrequence(limit, true));
	}

}
