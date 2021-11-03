package com.company.hw12.services;

import com.company.hw12.domains.Contact;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractFileContactsService implements ContactsService {

    protected final Path file;
    protected abstract List<Contact> load();

    protected abstract void save(List<Contact> contacts);

    private long getNextId(List<Contact> contacts) {
        return contacts.stream()
                .map(Contact::getId)
                .max(Long::compare)
                .orElse(0L) + 1;
    }

    public void checkForFileExisting(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Contact contact) {
        List<Contact> contacts = load();
        contact.setId(getNextId(contacts));
        contacts.add(contact);
        save(contacts);
    }

    @Override
    public void delete(Long id) {
        List<Contact> contacts = load().stream().filter(contact -> !contact.getId().equals(id)).collect(Collectors.toList());
        save(contacts);
    }

    @Override
    public List<Contact> getAll() {
        return load();
    }

    @Override
    public List<Contact> getByNamePart(String namePart) {
        return load().stream()
                .filter(contact -> contact.getName().contains(namePart))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> getByValueStart(String valueStart) {
        return load().stream()
                .filter(contact -> contact.getValue().contains(valueStart))
                .collect(Collectors.toList());
    }
}
