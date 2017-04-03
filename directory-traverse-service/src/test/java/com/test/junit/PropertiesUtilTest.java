package com.test.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.env.MockPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.test.enums.MessageCodes;
import com.test.util.PropertiesUtil;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class PropertiesUtilTest {

	//	@Mock
	//	private Environment environment;


	@InjectMocks
	PropertiesUtil propertiesUtil = new PropertiesUtil();

	@Test
	public void getPropertyValueTestIfKeyIsNullOrEmpty(){

		String propertyValue = propertiesUtil.getPropertyValue(null);

		assertNull("Key is null hence value should be null",propertyValue);
	}

	@Test
	public void getValidPropertyValueTest(){

		MockEnvironment environment = new MockEnvironment();
		environment.setProperty("00000", "Success");

		PropertiesUtil util = new PropertiesUtil();
		util.setEnvironment(environment);
		
		String propertyValue = util.getPropertyValue(MessageCodes.SUCCESS.getMessageCode());

		assertNotNull("Key is null hence value should be not null",propertyValue);
	}
	

}
