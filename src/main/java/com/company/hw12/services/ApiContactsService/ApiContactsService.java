package com.company.hw12.services.ApiContactsService;

import com.company.hw12.domains.Contact;
import com.company.hw12.services.ContactsService;

import java.util.List;

public class ApiContactsService implements ContactsService {

    ApiCommunicationServices  apiCommunicationServices = new ApiCommunicationServices();
    @Override
    public void add(Contact contact) {
        apiCommunicationServices.add(contact.getType().getName(),contact.getValue(),contact.getName());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Contact> getAll() {
        return null;
    }

    @Override
    public List<Contact> getByNamePart(String NamePart) {
        return null;
    }

    @Override
    public List<Contact> getByValueStart(String valueStart) {
        return null;
    }
}
