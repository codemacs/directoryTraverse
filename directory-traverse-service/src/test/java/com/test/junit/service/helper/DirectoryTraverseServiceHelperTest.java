package com.test.junit.service.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.constants.ApplicationConstants;
import com.test.data.vo.DirectoryStructure;
import com.test.data.vo.FileDetails;
import com.test.service.helper.DirectoryTraverseServiceHelper;

/**
 * 
 * @author Abdul
 *
 * This test case traverse give directory
 */
@RunWith(SpringRunner.class)
public class DirectoryTraverseServiceHelperTest {

	@InjectMocks
	DirectoryTraverseServiceHelper directoryTraverseServiceHelper = new DirectoryTraverseServiceHelper();

	@Test
	public void testLongFiles() throws IOException {

		String workSpaceDirPath = System.getProperty("user.dir");
		String directoryPath = workSpaceDirPath + "/src/test/sources/MyTestData";

		File directory = new File(directoryPath);
		String fileType = ApplicationConstants.LONG_FILES;
		String fileExtension = ApplicationConstants.textFileExtension;

		DirectoryStructure directoryStructure = directoryTraverseServiceHelper
				.traverseGivenDirectory(directory, fileType, fileExtension);

		assertNotNull(directoryStructure);
		List<FileDetails> files = directoryStructure.getFiles();

		assertEquals("Long files count", 1, files.size());

		assertEquals("Immidiate sub directories cont", 1, directoryStructure
				.getSubDirectories().size());

		assertEquals("One step down sub directories cont", 2, directoryStructure
				.getSubDirectories().get(0).getSubDirectories().size());

		assertEquals("First long file word count", 1328L, files.get(0).getWordCount()
				.longValue());

		assertEquals("Word Mumbai count", 80l, files.get(0)
				.getWordsRepeatingMoreThan50Times().get("mumbai").longValue());
	}

	@Test
	public void testShortFiles() throws IOException {

		String workSpaceDirPath = System.getProperty("user.dir");
		String directoryPath = workSpaceDirPath + "/src/test/sources/MyTestData/Folder01";

		File directory = new File(directoryPath);
		String fileType = ApplicationConstants.SHORT_FILES;
		String fileExtension = ApplicationConstants.textFileExtension;

		DirectoryStructure directoryStructure = directoryTraverseServiceHelper
				.traverseGivenDirectory(directory, fileType, fileExtension);

		assertNotNull(directoryStructure);
		List<FileDetails> files = directoryStructure.getFiles();

		assertEquals("Short files count", 0, files.size());

		List<DirectoryStructure> immediateSubDirectories = directoryStructure
				.getSubDirectories();
		assertEquals("Immidiate sub directories count", 2, immediateSubDirectories.size());

		for (DirectoryStructure subDirectoryStructure : immediateSubDirectories) {

			if("Folder02".equals( subDirectoryStructure.getDirectoryName())){
				
				assertEquals("Immidiate first sub directories short files count", 2, subDirectoryStructure.getFiles().size());
				
			}if("Folder01".equals( subDirectoryStructure.getDirectoryName())){
				
				assertEquals("Immidiate second sub directories short files count", 1, subDirectoryStructure.getFiles().size());
				assertEquals("First long file word count", 111L, subDirectoryStructure.getFiles().get(0).getWordCount()
						.longValue());
			}

		}


	}
}
