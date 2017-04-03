package com.test.data.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FileDetails {

	String fileName;

	String absoluteFilePath;

	Long wordCount;

	@JsonInclude(Include.NON_NULL)
	Map<String, Long> wordsRepeatingMoreThan50Times;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getWordCount() {
		return wordCount;
	}

	public void setWordCount(Long wordCount) {
		this.wordCount = wordCount;
	}

	public Map<String, Long> getWordsRepeatingMoreThan50Times() {
		return wordsRepeatingMoreThan50Times;
	}

	public void setWordsRepeatingMoreThan50Times(
			Map<String, Long> wordsRepeatingMoreThan50Times) {
		this.wordsRepeatingMoreThan50Times = wordsRepeatingMoreThan50Times;
	}

	public String getAbsoluteFilePath() {
		return absoluteFilePath;
	}

	public void setAbsoluteFilePath(String absoluteFilePath) {
		this.absoluteFilePath = absoluteFilePath;
	}

	@Override
	public String toString() {
		return "FileDetailsDTO [fileName=" + fileName + ", absoluteFilePath="
				+ absoluteFilePath + ", wordCount=" + wordCount
				+ ", wordsRepeatingMoreThan50Times=" + wordsRepeatingMoreThan50Times
				+ "]";
	}

}
