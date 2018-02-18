package com.masmovil.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by jcorredera on 17/02/18.
 */
@SpringBootApplication
@EnableSwagger2
public class MMPhonesApp {

	public static void main(String[] args) {
		SpringApplication.run(MMPhonesApp.class, args);
	}

}
