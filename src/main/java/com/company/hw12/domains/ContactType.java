package com.company.hw12.domains;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContactType {
    email("email"), phone("Phone number");
    private final String name;

    public int getId() {
        for (int i = 0; i < values().length; i++) {
            if (values()[i] == this) return i;
        }
        return 0;
    }
}
