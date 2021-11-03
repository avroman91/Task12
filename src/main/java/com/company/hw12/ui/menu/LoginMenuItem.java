package com.company.hw12.ui.menu;

import com.company.hw12.services.ApiAuthService;
import com.company.hw12.services.AuthService;

import java.util.Scanner;

public class LoginMenuItem extends BasicMenuItem {

    AuthService authService;
    boolean isAuth = false;

    public LoginMenuItem(String name) {
        super(name);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter login: ");
        String login = scanner.nextLine();
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();
        authService = new ApiAuthService();
        authService.login(login, password);
        isAuth = authService.isAuthorised();
    }

    @Override
    public boolean exit() {
        return isAuth;
    }
}
