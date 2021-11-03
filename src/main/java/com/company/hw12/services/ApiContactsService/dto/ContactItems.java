package com.company.hw12.services.ApiContactsService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactItems {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("value")
    private String value;
}