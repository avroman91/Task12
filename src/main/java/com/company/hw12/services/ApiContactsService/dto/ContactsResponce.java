package com.company.hw12.services.ApiContactsService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactsResponce{

	@JsonProperty("contacts")
	private List<ContactItems> contactItems;

	@JsonProperty("status")
	private String status;
}