package com.company.hw12.services;

import com.company.hw12.domains.Contact;

import java.util.List;

public interface ContactsService {
    void add(Contact contact);

    void delete(Long id);

    List<Contact> getAll();

    List<Contact> getByNamePart(String NamePart);

    List<Contact> getByValueStart(String valueStart);

}
