package com.company.hw12.ui.menu.contacts;

import com.company.hw12.domains.Contact;
import com.company.hw12.services.ContactsService;
import com.company.hw12.ui.menu.MenuItem;
import com.company.hw12.ui.views.ContactsView;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddContactMenuItem implements MenuItem {
    private final ContactsService contactsService;
    private final ContactsView contactsView;

    @Override
    public String getName() {
        return "Add new contact";
    }

    @Override
    public void run() {
        Contact contact = contactsView.readContact();
        contactsService.add(contact);
    }
}

