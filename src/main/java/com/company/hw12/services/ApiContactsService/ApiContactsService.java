package com.company.hw12.services.ApiContactsService;

import com.company.hw12.domains.Contact;
import com.company.hw12.services.ContactsService;

import java.util.List;

public class ApiContactsService implements ContactsService {

    ApiCommunicationServices apiCommunicationServices = new ApiCommunicationServices();

    @Override
    public void add(Contact contact) {
        apiCommunicationServices.add(contact.getType().getName(), contact.getValue(), contact.getName());
    }

    public boolean authorization(String login, String password) {
        return apiCommunicationServices.login(login, password);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Contact> getAll() {
        return apiCommunicationServices.getAllContacts();
    }

    @Override
    public List<Contact> getByNamePart(String namePart) {
        return apiCommunicationServices.find(false, namePart);
    }

    @Override
    public List<Contact> getByValueStart(String valueStart) {
        return apiCommunicationServices.find(true, valueStart);
    }
}
