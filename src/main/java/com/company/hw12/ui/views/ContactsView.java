package com.company.hw12.ui.views;

import com.company.hw12.domains.Contact;

import java.util.List;

public interface ContactsView {
    void show(List<Contact> contactList);

    Contact readContact();
}
