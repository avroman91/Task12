package com.company.hw12.services.ApiContactsService;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiContactsServices {
    public static void main(String[] args) {
        ApiContactsServices apiContactsServices = new ApiContactsServices();
        apiContactsServices.createNewAccount("vasia", "1990-02-04", "1111");
        apiContactsServices.login("vasia", "1111");
        apiContactsServices.add("email","oper@gnail.com", "vasia");
    }

    HttpClient httpClient = HttpClient.newBuilder().build();
    String registerUrl = "https://mag-contacts-api.herokuapp.com/register";
    String loginUrl = "https://mag-contacts-api.herokuapp.com/login";
    String addContactUrl = "https://mag-contacts-api.herokuapp.com/contacts/add";
    String findContactUrl = "https://mag-contacts-api.herokuapp.com/contacts/find";


    String token = null;
    boolean isAutrorised = false;




    public void createNewAccount(String name, String dateOfBorn, String password) {
        StringBuilder createNewUser = new StringBuilder();
        createNewUser.append("{\"login\":\")");
        createNewUser.append(name);
        createNewUser.append("\",\"password\":\"");
        createNewUser.append(password);
        createNewUser.append("\",\"date_born\":\"");
        createNewUser.append(dateOfBorn);
        createNewUser.append("\"}");
        post(createNewUser.toString(), registerUrl,false);
    }

    public void login(String name, String password) {
        StringBuilder login = new StringBuilder();
        login.append("{\"login\":\")");
        login.append(name);
        login.append("\",\"password\":\"");
        login.append(password);
        login.append("\"}");
        post(login.toString(), loginUrl,false);
    }

    public void add(String type, String value, String name) {
        StringBuilder addNewContact = new StringBuilder();
        addNewContact.append("{\"type\":\"");
        addNewContact.append(type);
        addNewContact.append("\",\"value\":\"");
        addNewContact.append(value);
        addNewContact.append("\",\"name\":\"");
        addNewContact.append(name);
        addNewContact.append("\"}");
        System.out.println(addNewContact.toString());
        post(addNewContact.toString(), addContactUrl,true);

    }

    public void find (String typeOfSearch, String value) {

    }

    public void post(String bodyPublisher, String url, boolean authorizationRequired) {
        HttpRequest httpRequestPost;
        if (isAutrorised && authorizationRequired) {
            httpRequestPost = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(bodyPublisher))
                    .header("Authorization","Bearer "+token)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .uri(URI.create(url))
                    .build();
        } else {
            httpRequestPost = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(bodyPublisher))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .uri(URI.create(url))
                    .build();
        }
        {
            try {
                HttpResponse<String> response = httpClient.send(httpRequestPost, HttpResponse.BodyHandlers.ofString());
                ObjectMapper objectMapper = new ObjectMapper();
                if (response.statusCode() == 200) {
                    String resp = response.body();
                    PrintStream out = new PrintStream(System.out, true, "UTF-8");
                    out.println(resp);
                    if (resp.substring(2, 7).equals("token")) {
                        LoginResponse loginResponse = objectMapper.readValue(resp, LoginResponse.class);
                        if (loginResponse.getStatus().equals("ok")) {
                            token = loginResponse.getToken();
                            isAutrorised = true;
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

