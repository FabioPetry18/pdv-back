package com.petry.pdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import co.elastic.apm.attach.ElasticApmAttacher;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableTransactionManagement
public class FpMsPdvApplication {
    private static final Logger logger = LoggerFactory.getLogger(FpMsPdvApplication.class);

 

	public static void main(String[] args) {
	
		SpringApplication.run(FpMsPdvApplication.class, args);

    }



}
