package com.nonstop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })
@SpringBootApplication
@ComponentScan(basePackages = {"com.nonstop"})
@ConfigurationPropertiesScan("com.nonstop.properties")
public class NonstopMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(NonstopMallApplication.class, args);
	}

}
