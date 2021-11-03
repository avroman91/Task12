package com.company.hw12.services;

import com.company.hw12.domains.Contact;
import com.company.hw12.domains.ContactType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvContactsService extends AbstractFileContactsService {
    public CsvContactsService(Path file) {
        super(file);
    }

    @Override
    protected List<Contact> load() {
        if (!Files.exists(file)) return new ArrayList<>();

        try {
            return Files.lines(file)
                    .map(s -> s.split(","))
                    .filter(arr -> arr.length == 4)
                    .map(arr -> new Contact()
                            .setId(Long.parseLong(arr[0]))
                            .setType(ContactType.values()[Integer.parseInt(arr[1])])
                            .setName(arr[2])
                            .setValue(arr[3]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("FAIL READ FILE");
        }
    }

    private String toCsvString(Contact contact) {
        StringBuilder sb = new StringBuilder();

        int typeId = contact.getType().getId();
        sb.append(contact.getId()).append(',')
                .append(typeId).append(',')
                .append(contact.getName()).append(',')
                .append(contact.getValue());
        return sb.toString();
    }

    @Override
    protected void save(List<Contact> contacts) {

        try {
            Files.write(file, contacts.stream().map(this::toCsvString).collect(Collectors.toList()), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
