package com.demo.protocol;

import lombok.Data;

@Data
public class HelloRequest {
	private String name;
	private String say;
	private Long randomId;
}
