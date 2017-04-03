package com.test.data.vo;

import java.util.List;

public class DirectoryStructure {

	String directoryName;
	List<FileDetails> files;
	List<DirectoryStructure> subDirectories;

	public DirectoryStructure() {
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public List<FileDetails> getFiles() {
		return files;
	}

	public void setFiles(List<FileDetails> files) {
		this.files = files;
	}

	public List<DirectoryStructure> getSubDirectories() {
		return subDirectories;
	}

	public void setSubDirectories(List<DirectoryStructure> subDirectories) {
		this.subDirectories = subDirectories;
	}

}
