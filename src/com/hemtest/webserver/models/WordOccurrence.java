package com.hemtest.webserver.models;

/**
 * WordOccurence Class - Containing the word and amount of occurrences
 *
 * @author anton lekedal (Doldas)
 *
 */
public class WordOccurrence {
	private String word; // the word
	private Long counter; // keeping track of occurrences

	public WordOccurrence() {
		this("");
	}

	public WordOccurrence(String word) {
		this.word = word;
		this.counter = 1L;
	}

	/**
	 * 
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Sets the word
	 * @param word the word to be set.
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Get the amount of occurrences
	 * @return
	 */
	public Long getCounter() {
		return counter;
	}

	/**
	 * increment the occurrences counter
	 */
	public void incrementCounter() {
		counter++;
	}
	/**
	 * Reset the occurrences counter to 1
	 */
	public void resetCounter() {
		counter = 1L;
	}

	/**
	 * Is it the same word?
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WordOccurrence)
			return this.word.equalsIgnoreCase(((WordOccurrence) obj).getWord());
		return false;
	}

	/**
	 * String representation
	 */
	@Override
	public String toString() {
		return word + ":" + counter;
	}
}
