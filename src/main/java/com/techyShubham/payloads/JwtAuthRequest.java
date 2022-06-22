package com.techyShubham.payloads;

import lombok.Data;

@Bean
@Data
public class JwtAuthRequest {

	private String username;
	private String password;

}
