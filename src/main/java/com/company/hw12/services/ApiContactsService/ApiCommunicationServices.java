package com.company.hw12.services.ApiContactsService;

import com.company.hw12.domains.Contact;
import com.company.hw12.domains.ContactType;
import com.company.hw12.services.ApiContactsService.dto.ContactItems;
import com.company.hw12.services.ApiContactsService.dto.ContactsResponce;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiCommunicationServices {
    public static void main(String[] args) {
//        ApiCommunicationServices apiCommunicationServices = new ApiCommunicationServices();
//        apiCommunicationServices.createNewAccount("vasia", "1990-02-04", "1111");
//        apiCommunicationServices.login("vasia", "1111");
//        apiCommunicationServices.add("email", "pizdec@qwerty.com", "Ololeg");
//        System.out.println(apiCommunicationServices.find(true, "oper@"));
//        System.out.println(apiCommunicationServices.getAllContacts());
    }

    private HttpClient httpClient = HttpClient.newBuilder().build();
    private StringBuilder sb = new StringBuilder();
    private String registerUrl = "https://mag-contacts-api.herokuapp.com/register";
    private String loginUrl = "https://mag-contacts-api.herokuapp.com/login";
    private String addContactUrl = "https://mag-contacts-api.herokuapp.com/contacts/add";
    private String findContactUrl = "https://mag-contacts-api.herokuapp.com/contacts/find";
    private String getAllContactsUrl = "https://mag-contacts-api.herokuapp.com/contacts";


    boolean isAutrorised;

    private String token;

    public void createNewAccount(String name, String dateOfBorn, String password) {
        sb.delete(0, sb.length());
        sb.append("{\"login\":\")");
        sb.append(name);
        sb.append("\",\"password\":\"");
        sb.append(password);
        sb.append("\",\"date_born\":\"");
        sb.append(dateOfBorn);
        sb.append("\"}");
        post(sb.toString(), registerUrl, false);
    }

    public boolean login(String name, String password) {
        sb.delete(0, sb.length());
        sb.append("{\"login\":\")");
        sb.append(name);
        sb.append("\",\"password\":\"");
        sb.append(password);
        sb.append("\"}");
        return post(sb.toString(), loginUrl, false).getStatus().equals("ok");
    }

    public void add(String type, String value, String name) {
        sb.delete(0, sb.length());
        sb.append("{\"type\":\"");
        sb.append(type);
        sb.append("\",\"value\":\"");
        sb.append(value);
        sb.append("\",\"name\":\"");
        sb.append(name);
        sb.append("\"}");
        System.out.println(sb.toString());
        post(sb.toString(), addContactUrl, true);

    }

    public List<Contact> find(boolean isValueSearch, String value) {
        sb.delete(0, sb.length());
        if (isValueSearch) {
            sb.append("{\"value\" : \"");
        } else {
            sb.append("{\"name\" : \"");
        }
        sb.append(value).append("\"}");

        return transformer(post(sb.toString(), findContactUrl, true).getContactItems());
    }

    public List<Contact> getAllContacts() {
        return transformer(get(getAllContactsUrl, true).getContactItems());
    }

    private List<Contact> transformer(List<ContactItems> contactItems) {
        List<Contact> answer = new ArrayList<>();
        for (ContactItems item : contactItems) {
            Contact contact = new Contact();
            contact.setId(item.getId().longValue());
            contact.setType(ContactType.valueOf(item.getType()));
            contact.setName(item.getName());
            contact.setValue(item.getValue());
            answer.add(contact);
        }
        return answer;
    }

    private ContactsResponce post(String bodyPublisher, String url, boolean authorizationRequired) {
        HttpRequest httpRequestPost;
        if (authorizationRequired) {
            httpRequestPost = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(bodyPublisher))
                    .header("Authorization", "Bearer " + token)
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
                    if (resp.substring(2, 7).equals("token")) {
                        LoginResponse loginResponse = objectMapper.readValue(resp, LoginResponse.class);
                        if (loginResponse.getStatus().equals("ok")) {
                            token = loginResponse.getToken();
                            isAutrorised = true;
                        }
                    }
                    return objectMapper.readValue(resp, ContactsResponce.class);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private ContactsResponce get(String url, boolean authorizationRequired) {
        HttpRequest httpRequestGet = null;
        if (isAutrorised && authorizationRequired) {
            httpRequestGet = HttpRequest.newBuilder()
                    .GET()
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .uri(URI.create(getAllContactsUrl))
                    .build();
        } else if (!authorizationRequired) {
            httpRequestGet = HttpRequest.newBuilder()
                    .GET()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .uri(URI.create(getAllContactsUrl))
                    .build();
        } else {
            return null;
        }
        try {
            HttpResponse<String> response = httpClient.send(httpRequestGet, HttpResponse.BodyHandlers.ofString());
            String resp = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(resp, ContactsResponce.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

