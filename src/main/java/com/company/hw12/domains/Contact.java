package com.company.hw12.domains;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Contact {
    private String name;
    private Long id;
    private ContactType type;
    private String value;
}
