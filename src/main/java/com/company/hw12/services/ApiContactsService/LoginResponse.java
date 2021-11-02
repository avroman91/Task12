package com.company.hw12.services.ApiContactsService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse{

	@JsonProperty("token")
	private String token;

	@JsonProperty("status")
	private String status;
}