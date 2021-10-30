package com.company.hw12.services;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InternalAuthService implements AuthService{

    private final String password;
    private final String login;
    private boolean isAuth = false;

    @Override
    public boolean login(String login, String password) {
        return isAuth = (this.login.equals(login) && this.password.equals(password));
    }

    @Override
    public boolean register(String login, String password, String birthDay) {
        return false;
    }

    @Override
    public boolean isAuthorised() {
        return isAuth;
    }
}
