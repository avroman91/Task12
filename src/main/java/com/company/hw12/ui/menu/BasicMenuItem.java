package com.company.hw12.ui.menu;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BasicMenuItem implements MenuItem {
    private final String name;

    @Override
    public String getName() {
        return name;
    }
}
