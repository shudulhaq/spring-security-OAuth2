package com.infosys.oauth2login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class Oauth2LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2LoginApplication.class, args);
	}
}
