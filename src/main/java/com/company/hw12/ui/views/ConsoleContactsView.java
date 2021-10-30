package com.company.hw12.ui.views;

import com.company.hw12.domains.Contact;
import com.company.hw12.domains.ContactType;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class ConsoleContactsView implements ContactsView {

    private final Scanner scanner;

    @Override
    public void show(List<Contact> contactList) {
        for (Contact c : contactList) {
            System.out.printf("%d - %s [%s] : %s\n", c.getId(), c.getName(), c.getType().getName(), c.getValue());
        }
    }

    @Override
    public Contact readContact() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Choose type: ");
        ContactType type = null;
        while (type == null) {
            ContactType[] types = ContactType.values();
            for (int i = 0; i < types.length; i++) {
                System.out.printf("\t%d - %s \n", i + 1, types[i].getName());
            }
            System.out.print("-> ");
            int ch = scanner.nextInt() - 1;
            scanner.nextLine();
            if (ch < 0 || ch > types.length) {
                System.out.println("Try again");
                continue;
            }
            type = types[ch];
        }
        System.out.println("Enter contact: ");
        String value = scanner.nextLine();
        return new Contact()
                .setName(name)
                .setType(type)
                .setValue(value);
    }
}
