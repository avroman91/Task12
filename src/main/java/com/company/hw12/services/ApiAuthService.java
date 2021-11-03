package com.company.hw12.services;

import com.company.hw12.services.ApiContactsService.ApiCommunicationServices;

public class ApiAuthService implements AuthService {
    ApiCommunicationServices apiCommunicationServices = new ApiCommunicationServices();
    private boolean isAuth = false;

    @Override
    public boolean login(String login, String password) {
        return isAuth = apiCommunicationServices.login(login, password);

    }

    @Override
    public boolean register(String login, String password, String birthDay) {
        return register(login, password, birthDay);
    }

    @Override
    public boolean isAuthorised() {
        return isAuth;
    }
}
