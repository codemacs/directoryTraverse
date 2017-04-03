package com.test.junit.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.validation.RequestValidator;

@RunWith(SpringRunner.class)
public class RequestValidatorTest {

	@InjectMocks
	RequestValidator requestValidator = new RequestValidator();

	@Test
	public void testIfGivenDirectoryPathIsPresent() {

		String workSpaceDirPath = System.getProperty("user.dir");

		assertTrue("Should pass as valid direcory name is given",requestValidator.validateDirectoryPath(workSpaceDirPath));
	}

	@Test
	public void testIfGivenDirectoryPathIsNotPresent() {

		StringBuilder workSpaceDirPath = new StringBuilder(System.getProperty("user.dir"));
		
		workSpaceDirPath.append("\\fakeDirectory\\");
		
		assertFalse("Should fail because give dir is not present",requestValidator.validateDirectoryPath(workSpaceDirPath.toString() ));
	}
}
