package com.test.junit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.boot.loader.BootLoaderClass;
import com.test.enums.MessageCodes;
import com.test.request.vo.RequestVO;
import com.test.response.vo.ResponseVO;
import com.test.service.DirectoryTraverseService;
import com.test.validation.RequestValidator;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BootLoaderClass.class)
public class DirectoryTraverseServiceControllerTest extends BootLoaderClass {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Mock
	private RequestValidator requestValidator;
	
	@Mock
	private DirectoryTraverseService directoryTraverseService;

	@Test
	public void testIfDirectoyRequestIsNull() throws Exception {

		StringBuilder url = new StringBuilder("http://").append("localhost").append(":")
				.append(port).append("/api/service/getdirectorydetails");

		RequestVO requestVO = null;

		ResponseEntity<ResponseVO> responseEntity = this.restTemplate.postForEntity(
				url.toString(), requestVO, ResponseVO.class);

		assertEquals("Expected HTTP status respone not received",
				HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseEntity.getStatusCode());
	}

	@Test
	public void testIfDirectoyPathIsNull() throws Exception {

		String expectedMessageCode = MessageCodes.EMPTY_DIRECTORY_PATH.getMessageCode();

		StringBuilder url = new StringBuilder("http://").append("localhost").append(":")
				.append(port).append("/api/service/getdirectorydetails");

		RequestVO requestVO = new RequestVO();

		ResponseEntity<ResponseVO> responseEntity = this.restTemplate.postForEntity(
				url.toString(), requestVO, ResponseVO.class);

		assertNotNull("Response entity must not be null", responseEntity.getBody());
		assertEquals("Expected message code not received", expectedMessageCode,
				responseEntity.getBody().getMessageCode());
		assertEquals("Expected HTTP status respone not received",
				HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

	@Test
	public void testIfDirectoyPathIsInvalid() throws Exception {

		String expectedMessageCode = MessageCodes.INVALID_DIRECTORY_PATH.getMessageCode();

		StringBuilder url = new StringBuilder("http://").append("localhost").append(":")
				.append(port).append("/api/service/getdirectorydetails");

		RequestVO requestVO = new RequestVO();
		String directoryPath = "/&%$";
		requestVO.setDirectoryPath(directoryPath);

		Mockito.when(requestValidator.validateDirectoryPath(directoryPath)).thenReturn(false);

		ResponseEntity<ResponseVO> responseEntity = this.restTemplate.postForEntity(
				url.toString(), requestVO, ResponseVO.class);

		assertNotNull("Response entity must not be null", responseEntity.getBody());
		assertEquals("Expected message code not received", expectedMessageCode,
				responseEntity.getBody().getMessageCode());
		assertEquals("Expected HTTP status respone not received",
				HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

	@Test
	public void testIfDirectoyPathIsValid() throws Exception {

		String expectedMessageCode = null;

		StringBuilder url = new StringBuilder("http://").append("localhost").append(":")
				.append(port).append("/api/service/getdirectorydetails");

		RequestVO requestVO = new RequestVO();
		String workSpaceDirPath = System.getProperty("user.dir");
		String directoryPath = workSpaceDirPath + "\\src\\test\\sources\\MyTestData";
		requestVO.setDirectoryPath(directoryPath);
		Mockito.when(requestValidator.validateDirectoryPath(directoryPath)).thenReturn(false);

		ResponseEntity<ResponseVO> responseEntity = this.restTemplate.postForEntity(
				url.toString(), requestVO, ResponseVO.class);

		assertNotNull("Response entity must not be null", responseEntity.getBody());
		assertEquals("Expected message code not received", expectedMessageCode,
				responseEntity.getBody().getMessageCode());
		assertEquals("Expected HTTP status respone not received", HttpStatus.OK,
				responseEntity.getStatusCode());
	}
	
}
