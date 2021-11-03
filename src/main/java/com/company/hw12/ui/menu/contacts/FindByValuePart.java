package com.company.hw12.ui.menu.contacts;

import com.company.hw12.domains.Contact;
import com.company.hw12.services.ContactsService;
import com.company.hw12.ui.menu.MenuItem;
import com.company.hw12.ui.views.ContactsView;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class FindByValuePart implements MenuItem {
    private final ContactsService contactsService;
    private final ContactsView contactsView;
    private final Scanner scanner;

    @Override
    public String getName() {
        return "Find contact by value part";
    }

    @Override
    public void run() {
        System.out.println("Enter part of the contact value: ");
        List<Contact> contacts = contactsService.getByValueStart(scanner.nextLine());
        contactsView.show(contacts);
    }
}
