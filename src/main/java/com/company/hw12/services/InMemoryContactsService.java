package com.company.hw12.services;

import com.company.hw12.domains.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryContactsService implements ContactsService {
    private List<Contact> contacts = new ArrayList<>();


    private long getNextId() {
        return contacts.stream()
                .map(Contact::getId)
                .max(Long::compare)
                .orElse(0L) + 1;
    }

    @Override
    public void add(Contact contact) {
        contact.setId(getNextId());
        contacts.add(contact);
    }

    @Override
    public void delete(Long id) {
        contacts = new ArrayList<>(contacts.stream()
                .filter(contact -> !contact.getId().equals(id))
                .collect(Collectors.toList()));

    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }

    @Override
    public List<Contact> getByNamePart(String namePart) {
        return contacts.stream()
                .filter(contact -> contact.getName().contains(namePart))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> getByValueStart(String valueStart) {
        return contacts.stream()
                .filter(contact -> contact.getValue().contains(valueStart))
                .collect(Collectors.toList());
    }
}
