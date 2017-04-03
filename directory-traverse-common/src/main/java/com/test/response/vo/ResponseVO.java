package com.test.response.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.test.data.vo.DirectoryStructure;

@JsonInclude(Include.NON_NULL)
public class ResponseVO {

	String messageCode;
	String message;
	DirectoryStructure longFiles;
	DirectoryStructure shortFiles;

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DirectoryStructure getLongFiles() {
		return longFiles;
	}

	public void setLongFiles(DirectoryStructure longFiles) {
		this.longFiles = longFiles;
	}

	public DirectoryStructure getShortFiles() {
		return shortFiles;
	}

	public void setShortFiles(DirectoryStructure shortFiles) {
		this.shortFiles = shortFiles;
	}
}
