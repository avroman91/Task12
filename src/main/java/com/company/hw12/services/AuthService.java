package com.company.hw12.services;

public interface AuthService {

    boolean login (String login, String password);
    boolean register (String login, String password, String birthDay);
    boolean isAuthorised();
}
