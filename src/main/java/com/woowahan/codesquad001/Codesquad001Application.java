package com.woowahan.codesquad001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan(basePackages="com.woowahan.codesquad001")
@SpringBootApplication
@EnableJpaAuditing
public class Codesquad001Application {

	public static void main(String[] args) {
		SpringApplication.run(Codesquad001Application.class, args);
	}
}
