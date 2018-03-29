package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class ZipKinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipKinApplication.class, args);
	}
}