package com.test.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.data.vo.DirectoryStructure;
import com.test.data.vo.FileDetails;
import com.test.request.vo.RequestVO;
import com.test.response.vo.ResponseVO;

public class RequestProcessor {

	public static final String serviceName = "/api/service/getdirectorydetails";

	public void processPosRequest(String hostAddress, String hostPort, String filePath) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			StringBuilder url = new StringBuilder("http://");
			url.append(hostAddress).append(":").append(hostPort).append(serviceName);

			RequestVO requestVO = new RequestVO();
			requestVO.setDirectoryPath(filePath);

			ResponseVO responseVO = restTemplate.postForObject(url.toString(), requestVO,
					ResponseVO.class);

			this.display(responseVO);
		}
		catch (Exception e) {
			handleError(e);
		}
	}

	private void handleError(Exception e) {

		String responseBodyAsString = null;
		ResponseVO responseVO = null;
		int statusCode = 400;
		ObjectMapper objectMapper = new ObjectMapper();

		if (e instanceof HttpClientErrorException) {

			HttpClientErrorException httpClientErrorException = (HttpClientErrorException) e;
			statusCode = httpClientErrorException.getRawStatusCode();
			responseBodyAsString = httpClientErrorException.getResponseBodyAsString();

		}
		else if (e instanceof HttpServerErrorException) {

			RestClientResponseException restClientResponseException = (RestClientResponseException) e;

			statusCode = restClientResponseException.getRawStatusCode();

			responseBodyAsString = restClientResponseException.getResponseBodyAsString();
		}

		try {
			responseVO = objectMapper.readValue(responseBodyAsString, ResponseVO.class);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}

		System.err.println("HTTP status code: " + statusCode);
		System.err.println("Message from server: " + responseVO.getMessage());

	}

	private void display(ResponseVO responseVO) {

		System.out
				.println("===============================================================================================");
		System.out.println("\n");
		System.out.println("**** LONG FILES ****");
		this.show(responseVO.getLongFiles(), 0);
		System.out.println("\n");
		System.out.println("**** SHORT FILES ****");
		this.show(responseVO.getShortFiles(), 0);
		System.out.println("\n");
		System.out
				.println("===============================================================================================");
	}

	private void show(DirectoryStructure longFiles, int tabCount) {

		if (null != longFiles) {
			for (int i = 0; i < tabCount; i++) {
				System.out.print("\t");
			}

			System.out.println(longFiles.getDirectoryName());

			List<FileDetails> files = longFiles.getFiles();

			for (FileDetails fileDetails : files) {
				for (int i = 0; i < tabCount + 1; i++) {
					System.out.print("\t");
				}

				Map<String, Long> wordsRepeatingMoreThan50Times = fileDetails
						.getWordsRepeatingMoreThan50Times();

				StringBuilder wordMap = null;
				if (null != wordsRepeatingMoreThan50Times
						&& !wordsRepeatingMoreThan50Times.isEmpty()) {
					wordMap = getLongWordCount(wordsRepeatingMoreThan50Times);
				}
				System.out.println(fileDetails.getFileName() + " "
						+ fileDetails.getWordCount() + " " + wordMap);
			}

			List<DirectoryStructure> subDirectories = longFiles.getSubDirectories();

			for (DirectoryStructure directoryStructureDTO : subDirectories) {
				show(directoryStructureDTO, tabCount = tabCount + 1);
			}
		}
	}

	private StringBuilder getLongWordCount(Map<String, Long> wordsRepeatingMoreThan50Times) {

		StringBuilder result = new StringBuilder();

		for (String key : wordsRepeatingMoreThan50Times.keySet()) {
			result.append(key + " " + wordsRepeatingMoreThan50Times.get(key) + " ");
		}

		return result;
	}
}
