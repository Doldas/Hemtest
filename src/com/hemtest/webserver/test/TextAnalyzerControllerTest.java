package com.hemtest.webserver.test;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
/**
 * JUnit testing for the TextAnalyzerController
 *
 * @author anton lekedal (Doldas)
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TextAnalyzerControllerTest {
	@Autowired
	private MockMvc mvc;
	@Test
	public void getWordOccurrences() throws Exception {
		String text = "Banana Apple Cat Dog Banana Dog Cat Dog";
		mvc.perform(MockMvcRequestBuilders.post("/count").content(text).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{Dog:3,Banana:2,Cat:2,Apple:1}")));
	}
	@Test
	public void getWordOccurrencesIgnoreSpecialChars() throws Exception {
		String text = "<Banana> {Apple} Cat; Dog: Banana? Dog* Cat\n Dog";
		mvc.perform(MockMvcRequestBuilders.post("/count").content(text).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{Dog:3,Banana:2,Cat:2,Apple:1}")));
	}
	@Test
	public void getWordOccurrencesTenOccurencesMaximumGivenThirteenUniqueWords() throws Exception {
		String text = "<Banana> {Apple} Cat; Dog: Banana? Dog* Cat\n Dog Horse, Snake. Lizard Yellyfish, Donkey, Moose, Rat Rat Rat Rat Scorpion Scorpion Bird Bird Bird";
		mvc.perform(MockMvcRequestBuilders.post("/count").content(text).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{Rat:4,Dog:3,Bird:3,Banana:2,Cat:2,Scorpion:2,Apple:1,Horse:1,Snake:1,Lizard:1}")));
	}
}