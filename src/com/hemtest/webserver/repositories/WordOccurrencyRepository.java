package com.hemtest.webserver.repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import com.hemtest.webserver.models.WordOccurrence;
/**
 * The Word Occurrences Repository
 *
 * @author anton lekedal (Doldas)
 *
 */
public class WordOccurrencyRepository implements IRepository<WordOccurrence> {
	private List<WordOccurrence> storage; //storage

	public WordOccurrencyRepository() {
		storage = new ArrayList<>();
	}

	@Override
	public void create(WordOccurrence newObj) {
		if (storage.contains(newObj))
			storage.stream().filter(w -> w.equals(newObj)).findFirst().get().incrementCounter(); //increment the counter
		else {
			storage.add(newObj);
		}
	}

	/**
	 * Sort Words
	 * @param limit limits the amount of words to be shown
	 * @param desc Descending order?
	 * @return A sorted stream of word occurrences
	 */
	public Stream<WordOccurrence> getSortedStreamByFrequence(int limit, boolean desc) {
		if (desc)
			return storage.stream().sorted(Comparator.comparingLong(WordOccurrence::getCounter).reversed()).limit(limit);
		return storage.stream().sorted(Comparator.comparingLong(WordOccurrence::getCounter)).limit(limit);
	}

	/**
	 * a string representation of the whole storage
	 */
	@Override
	public String toString() {
		return toString(storage.stream());
	}
}
