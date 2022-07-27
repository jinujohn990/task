package com.test.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import com.test.task.dto.WordVowelsDetails;

/**
 * Task
 *
 */
public class App {
	private static List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o'));

	public static void main(String[] args) {
		Map<String, List<WordVowelsDetails>> vowelMap = new HashMap<String, List<WordVowelsDetails>>();
		try (BufferedReader br = new BufferedReader(new FileReader("INPUT.TXT"))) {
			String line = br.readLine();
			while (line != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					Set<Character> vowelsPresent = new TreeSet<>();
					String word1 = word.toLowerCase();
					word1 = word1.replace(".", "");
					char[] letters = word1.toCharArray();
					int count = 0;
					for (char letter : letters) {
						if (Character.isAlphabetic(letter) && letter != '.' && vowels.contains(letter)) {
							vowelsPresent.add(letter);
							count++;
						}
					}
					String vowelCharactersPresent = joinCharacter(vowelsPresent);
					if (vowelMap.containsKey(vowelCharactersPresent)) {
						CopyOnWriteArrayList<WordVowelsDetails> wordVowelsDetailsList = (CopyOnWriteArrayList<WordVowelsDetails>) vowelMap
								.get(vowelCharactersPresent);
						Iterator<WordVowelsDetails> iterator = wordVowelsDetailsList.listIterator();
						while (iterator.hasNext()) {
							WordVowelsDetails wordVowelsDetails = iterator.next();
							if (wordVowelsDetails.getWordTotalCount() == word1.length()) {
								wordVowelsDetails.setTotalNoOfWords(wordVowelsDetails.getTotalNoOfWords() + 1);
								wordVowelsDetails
										.setVowerlsTotalCount(wordVowelsDetails.getVowerlsTotalCount() + count);
							} else {
								List<WordVowelsDetails> vowelsDetailsList = new CopyOnWriteArrayList<>();
								vowelsDetailsList.add(new WordVowelsDetails(count, word1.length()));
								vowelMap.get(vowelCharactersPresent).add(new WordVowelsDetails(count, word1.length()));
							}
						}

					} else {
						List<WordVowelsDetails> vowelsDetailsList = new CopyOnWriteArrayList<>();
						vowelsDetailsList.add(new WordVowelsDetails(count, word1.length()));
						vowelMap.put(vowelCharactersPresent, vowelsDetailsList);
					}
				}
				line = br.readLine();
			}
			for (String key : vowelMap.keySet()) {
				List<WordVowelsDetails> wordVowelsDetails = vowelMap.get(key);
				for (WordVowelsDetails details : wordVowelsDetails) {
					System.out.println("({" + key + "}, " + details.getWordTotalCount() + " -> "
							+ (float) details.getVowerlsTotalCount() / (float) details.getTotalNoOfWords());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String joinCharacter(Set<Character> vowelsPresent) {
		return vowelsPresent.stream().map(c -> Character.toString(c)).collect(Collectors.joining(","));
	}

}
