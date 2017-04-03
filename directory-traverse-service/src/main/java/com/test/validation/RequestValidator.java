package com.test.validation;

import java.io.File;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Abdul
 * 
 * This class contains functionality to validate incoming service request
 */
@Component
public class RequestValidator {

	/**
	 *  If given directoryPath is present then returns true else returns false
	 *  
	 * @param directoryPath
	 * @return
	 */
	public boolean validateDirectoryPath(String directoryPath){

		boolean isValidDir = false;

		File f = new File(directoryPath);
		if (f.exists() && f.isDirectory()) {
			isValidDir = true;
		}

		return isValidDir;
	}
}
