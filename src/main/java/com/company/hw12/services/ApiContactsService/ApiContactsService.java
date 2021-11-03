package com.company.hw12.services.ApiContactsService;

import com.company.hw12.domains.Contact;
import com.company.hw12.services.ContactsService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ApiContactsService implements ContactsService {

    ApiCommunicationServices apiCommunicationServices = new ApiCommunicationServices();
    private final String login;
    private final String password;

    @Override
    public void add(Contact contact) {
        authorization();
        apiCommunicationServices.add(contact.getType().getName(), contact.getValue(), contact.getName());
    }

    public void authorization() {
        apiCommunicationServices.login(login, password);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Contact> getAll() {
        authorization();
        return apiCommunicationServices.getAllContacts();
    }

    @Override
    public List<Contact> getByNamePart(String namePart) {
        authorization();
        return apiCommunicationServices.find(false, namePart);
    }

    @Override
    public List<Contact> getByValueStart(String valueStart) {
        authorization();
        return apiCommunicationServices.find(true, valueStart);
    }
}
