package com.trainer.mstrainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsTrainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTrainerApplication.class, args);
	}

}
