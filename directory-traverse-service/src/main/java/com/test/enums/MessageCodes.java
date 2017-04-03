package com.test.enums;

/**
 * 
 * @author Abdul
 * 
 *  Application specific error code are defined in this class
 *  This enum holds error code for which corresponding error messages are places in .properties file
 *  
 */
public enum MessageCodes {

	SUCCESS("00000"),GENERIC_FAILURE("00001"), EMPTY_DIRECTORY_PATH("00002"), INVALID_DIRECTORY_PATH(
			"00003"), ERROR_WHILE_ACCESSING_DIR("00004");

	private String messageCode;

	MessageCodes(String messageCode) {

		this.messageCode = messageCode;
	}

	public String getMessageCode() {

		return this.messageCode;
	}

}
