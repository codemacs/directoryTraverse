package com.test.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.constants.ApplicationConstants;
import com.test.data.vo.DirectoryStructure;
import com.test.request.vo.RequestVO;
import com.test.response.vo.ResponseVO;
import com.test.service.DirectoryTraverseService;
import com.test.service.helper.DirectoryTraverseServiceHelper;
import com.test.util.PropertiesUtil;

/**
 * 
 * @author Abdul
 *
 *
 * This is implementation of interface DirectoryTraverseService
 */
@Service
public class DirectoryTraverseServiceImpl implements DirectoryTraverseService {

	private Logger logger = Logger.getLogger(DirectoryTraverseServiceImpl.class);

	@Autowired
	private DirectoryTraverseServiceHelper directoryTraverseServiceHelper;

	@Autowired
	private PropertiesUtil propertiesUtil;

	/*
	 * (non-Javadoc)
	 * @see com.test.service.DirectoryTraverseService#processRequest(com.test.request.vo.RequestVO)
	 */
	@Override
	public ResponseVO processRequest(final RequestVO requestVO) throws IOException {

		logger.debug("Directory traverse service started");

		final File directory = new File(requestVO.getDirectoryPath());
		final ResponseVO responseVO = new ResponseVO();

		DirectoryStructure directoryStructureForLongFiles = null;
		DirectoryStructure directoryStructureForShortFiles = null;

		/*
		 * traverse first time to find directory details containing long files
		 */
		directoryStructureForLongFiles = directoryTraverseServiceHelper
				.traverseGivenDirectory(directory, ApplicationConstants.LONG_FILES,
						ApplicationConstants.textFileExtension);

		/*
		 * traverse second time to find directory details containing short files
		 */
		directoryStructureForShortFiles = directoryTraverseServiceHelper
				.traverseGivenDirectory(directory, ApplicationConstants.SHORT_FILES,
						ApplicationConstants.textFileExtension);

		responseVO.setLongFiles(directoryStructureForLongFiles);
		responseVO.setShortFiles(directoryStructureForShortFiles);

		logger.debug("Directory traverse service completed");

		return responseVO;
	}
}
