package com.hemtest.webserver.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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
	@Value("${words.counter.display.max-words:10}")
	private Integer LIMIT; // amount of words to be shown

	@Override
	@Async("wordexecutioner")
	public CompletableFuture<String> execute(String data) {
		return execute(data, LIMIT);
	}

	/**
	 * Executes the service with an additional parameter
	 * 
	 * @param data  the data to be processed
	 * @param limit The amount of words to be returned
	 * @return a sorted list of word occurrences limited by limit
	 */
	@Async("wordexecutioner")
	public CompletableFuture<String> execute(String data, Integer limit) {
		WordOccurrencyRepository repo = new WordOccurrencyRepository();
		for (String word : data.split(DELIMITERS)) {
			if (word.length() > 1)
				word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
			else if (word.length() == 1)
				word = word.substring(0, 1).toUpperCase();
			repo.create(new WordOccurrence(word));
		}
		return CompletableFuture.completedFuture(repo.toString(repo.getSortedStreamByFrequence(limit, true)));
	}
}
