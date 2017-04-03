package com.test.boot.loader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Abdul
 * 
 * Spring boot loader class
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.test")
public class BootLoaderClass {

	public static void main(String[] args) {

		SpringApplication.run(BootLoaderClass.class, args);
	}
}
