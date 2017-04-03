package com.test.service;

import java.io.IOException;

import com.test.request.vo.RequestVO;
import com.test.response.vo.ResponseVO;

/**
 * 
 * @author Abdul
 *
 * Directory traverse service interface
 */
public interface DirectoryTraverseService {

	/**
	 * This method process the request received from controller, traverse through given directory to find out the details
	 *  
	 * @param requestVO
	 * @return
	 * @throws IOException
	 */
	ResponseVO processRequest(RequestVO requestVO) throws IOException;
}
