package com.company.hw12.ui.menu.contacts;

import com.company.hw12.domains.Contact;
import com.company.hw12.services.ContactsService;
import com.company.hw12.ui.menu.MenuItem;
import com.company.hw12.ui.views.ContactsView;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowAllMenuItem implements MenuItem {
    private final ContactsService contactsService;
    private final ContactsView contactsView;

    @Override
    public String getName() {
        return "Show all contacts";
    }

    @Override
    public void run() {
        List<Contact> contacts = contactsService.getAll();
        contactsView.show(contacts);
    }
}

