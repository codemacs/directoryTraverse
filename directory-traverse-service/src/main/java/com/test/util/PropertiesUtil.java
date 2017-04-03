package com.test.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import com.test.service.helper.DirectoryTraverseServiceHelper;

/**
 * 
 * @author Abdul
 * 
 *  This class used to get value of given key from messages.properties.class
 *  
 */
@Configuration
@ComponentScan(basePackages = "com.test")
@PropertySource("classpath:messages.properties")
public class PropertiesUtil {

	private Logger logger = Logger.getLogger(DirectoryTraverseServiceHelper.class);

	/**
	 * Environment API
	 */
	@Autowired
	private Environment environment;

	/**
	 *  This methods takes key as parameter and return value of key from properties file
	 * @param propertyName
	 * @return
	 */
	public String getPropertyValue(String propertyName) {

		if (StringUtils.isEmpty(propertyName)) {
			return null;
		}

		String propertyValue = environment.getRequiredProperty(propertyName);

		if (StringUtils.isEmpty(propertyValue)) {
			logger.error("Property " + propertyName + " is missing in properties file");
		}

		return propertyValue;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
}