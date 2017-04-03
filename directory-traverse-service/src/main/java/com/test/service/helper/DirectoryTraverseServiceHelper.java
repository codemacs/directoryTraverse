package com.test.service.helper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.test.constants.ApplicationConstants;
import com.test.data.vo.DirectoryStructure;
import com.test.data.vo.FileDetails;

/**
 * 
 * @author Abdul
 *
 * This class contains methods to traverse and find details recursively 
 */
@Component
public class DirectoryTraverseServiceHelper {

	private Logger logger = Logger.getLogger(DirectoryTraverseServiceHelper.class);

	/**
	 * 
	 * @param directory      - given directory path
	 * @param fileType       - LONG_FILES / SHORT_FILES
	 * @param fileExtension  - .txt
	 * @return
	 * @throws IOException
	 */
	public DirectoryStructure traverseGivenDirectory(File directory, String fileType,
			String fileExtension) throws IOException {

		File[] fList = directory.listFiles();
		DirectoryStructure directoryStructure = new DirectoryStructure();
		directoryStructure.setDirectoryName(directory.getName());

		List<FileDetails> filesInThisDir = new ArrayList<FileDetails>();
		List<DirectoryStructure> subDirectoriesInThisDir = new ArrayList<DirectoryStructure>();

		if (null != fList) {

			for (File file : fList) {

				if (file.isFile() && file.getName().endsWith(fileExtension)) {

					FileDetails fileDetailsDTO = this.getFileDetails(file);

					if (1000 <= fileDetailsDTO.getWordCount()
							&& ApplicationConstants.LONG_FILES.equals(fileType)) {

						filesInThisDir.add(fileDetailsDTO);
					}
					else if (1000 > fileDetailsDTO.getWordCount()
							&& ApplicationConstants.SHORT_FILES.equals(fileType)) {
						filesInThisDir.add(fileDetailsDTO);
					}
				}
				else if (file.isDirectory()) {

					/*
					 * calling method recursively
					 */
					DirectoryStructure subDirectories = traverseGivenDirectory(file,
							fileType, fileExtension);
					subDirectoriesInThisDir.add(subDirectories);
				}
			}
		}

		subDirectoriesInThisDir.removeAll(Collections.singleton(null));

		directoryStructure.setFiles(filesInThisDir);
		directoryStructure.setSubDirectories(subDirectoriesInThisDir);

		return directoryStructure;
	}

	/**
	 * Get file details 
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private FileDetails getFileDetails(File file) throws IOException {

		FileInputStream fis = null;
		DataInputStream dis = null;
		BufferedReader br = null;
		Map<String, Long> wordMap = new HashMap<String, Long>();

		FileDetails fileDetailsO = new FileDetails();
		Map<String, Long> wordsRepeatingMoreThan50Times = new HashMap<String, Long>();
		Long totalWordCountInFile = 0L;

		try {

			fis = new FileInputStream(file);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));
			String line = null;

			while ((line = br.readLine()) != null) {

				StringTokenizer st = new StringTokenizer(line, ApplicationConstants.SPACE_LETERAL);
				
				while (st.hasMoreTokens()) {
					
					totalWordCountInFile++;
					String tmp = st.nextToken().toLowerCase();

					if (wordMap.containsKey(tmp)) {
						Long newCount = wordMap.get(tmp) + 1;

						wordMap.put(tmp, newCount);

						/*
						 * if word count is more than 50 times 
						 */
						if (newCount > 50) {
							wordsRepeatingMoreThan50Times.put(tmp, newCount);
						}
					}
					else {
						wordMap.put(tmp, 1L);
					}
				}
			}

			fileDetailsO.setAbsoluteFilePath(file.getAbsolutePath());
			fileDetailsO.setFileName(file.getName());
			fileDetailsO.setWordCount(totalWordCountInFile);
			fileDetailsO.setWordsRepeatingMoreThan50Times(wordsRepeatingMoreThan50Times);

			return fileDetailsO;
		}
		finally {
			try {
				if (br != null)
					br.close();
			}
			catch (Exception e) {
				logger.error("Error occured while closing bufferred reader:", e);
			}
		}
	}
}
