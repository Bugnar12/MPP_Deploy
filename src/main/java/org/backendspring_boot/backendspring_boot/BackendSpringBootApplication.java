package org.backendspring_boot.backendspring_boot;

import org.backendspring_boot.backendspring_boot.controller.AntivirusController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.backendspring_boot.backendspring_boot.service.AntivirusServiceImpl;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@ComponentScan({"org.example.service", "org.example.controller", "org.example.config"})
public class BackendSpringBootApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(BackendSpringBootApplication.class, args);
		Object service = applicationContext.getBean(AntivirusServiceImpl.class);
		//create the controller
		AntivirusController controller = new AntivirusController((AntivirusServiceImpl) service);
		//run the controller
	}

}
