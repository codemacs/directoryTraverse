package com.test.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enums.MessageCodes;
import com.test.request.vo.RequestVO;
import com.test.response.vo.ResponseVO;
import com.test.service.DirectoryTraverseService;
import com.test.util.PropertiesUtil;
import com.test.validation.RequestValidator;

/**
 * 
 * @author Abdul
 *
 * Rest controller to accept the HTTP request
 */
@RestController
@RequestMapping("api/service/")
public class DirectoryTraverseServiceController {

	private Logger logger = Logger.getLogger(DirectoryTraverseServiceController.class);

	@Autowired
	private PropertiesUtil propertiesUtil;

	@Autowired
	private DirectoryTraverseService directoryTraverseService;

	@Autowired
	private RequestValidator requestValidator;

	/**
	 *  This service receives JSON for type RequestVO 
	 *  
	 * @param requestVO
	 * @return
	 */
	@RequestMapping(value = "/getdirectorydetails", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> getDirectoryDetails(
			@RequestBody RequestVO requestVO) {

		if (StringUtils.isEmpty(requestVO.getDirectoryPath())) {

			String messageCode = MessageCodes.EMPTY_DIRECTORY_PATH.getMessageCode();
			String message = propertiesUtil.getPropertyValue(messageCode);

			logger.error(message);

			ResponseVO responseVO = new ResponseVO();
			responseVO.setMessageCode(messageCode);
			responseVO.setMessage(message);

			return new ResponseEntity<>(responseVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!requestValidator.validateDirectoryPath(requestVO.getDirectoryPath())) {

			ResponseVO responseVO = new ResponseVO();
			String messageCode = MessageCodes.INVALID_DIRECTORY_PATH.getMessageCode();
			responseVO.setMessageCode(messageCode);
			responseVO.setMessage(propertiesUtil.getPropertyValue(messageCode));

			return new ResponseEntity<>(responseVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {

			ResponseVO responseVO = directoryTraverseService.processRequest(requestVO);
			return new ResponseEntity<>(responseVO, HttpStatus.OK);
		}
		catch (IOException e) {

			ResponseVO responseVO = new ResponseVO();
			String messageCode = MessageCodes.ERROR_WHILE_ACCESSING_DIR.getMessageCode();
			responseVO.setMessageCode(messageCode);
			responseVO.setMessage(messageCode);
			responseVO.setMessage(propertiesUtil.getPropertyValue(messageCode));
			logger.error("Error occurred while accessing directory:", e);
			return new ResponseEntity<>(responseVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
