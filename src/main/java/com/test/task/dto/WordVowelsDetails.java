package com.test.task.dto;

public class WordVowelsDetails {

	private int vowerlsTotalCount;
	private int wordTotalCount;
	private int totalNoOfWords;

	public int getTotalNoOfWords() {
		return totalNoOfWords;
	}

	public void setTotalNoOfWords(int totalNoOfWords) {
		this.totalNoOfWords = totalNoOfWords;
	}

	public WordVowelsDetails(int vowerlsTotalCount, int wordTotalCount) {
		this.vowerlsTotalCount = vowerlsTotalCount;
		this.wordTotalCount = wordTotalCount;
		this.totalNoOfWords = 1;
	}

	public int getWordTotalCount() {
		return wordTotalCount;
	}

	public void setWordTotalCount(int wordTotalCount) {
		this.wordTotalCount = wordTotalCount;
	}

	public int getVowerlsTotalCount() {
		return vowerlsTotalCount;
	}

	public void setVowerlsTotalCount(int vowerlsTotalCount) {
		this.vowerlsTotalCount = vowerlsTotalCount;
	}

}
