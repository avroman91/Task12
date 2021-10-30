package com.company.hw12;

import com.company.hw12.services.AuthService;
import com.company.hw12.services.ContactsService;
import com.company.hw12.services.CsvContactsService;
import com.company.hw12.services.InternalAuthService;
import com.company.hw12.ui.menu.ExitMenuItem;
import com.company.hw12.ui.menu.Menu;
import com.company.hw12.ui.menu.MenuItem;
import com.company.hw12.ui.menu.contacts.AddContactMenuItem;
import com.company.hw12.ui.menu.contacts.ShowAllMenuItem;
import com.company.hw12.ui.views.ConsoleContactsView;
import com.company.hw12.ui.views.ContactsView;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AuthService authService = new InternalAuthService("admin", "admin");

        Menu authMenu = new Menu(scanner,List.of(new ExitMenuItem("Exit")));
        authMenu.run();


        if (authService.isAuthorised()) {
            ContactsService contactsService = new CsvContactsService(Path.of("1.csv"));
            ContactsView contactsView = new ConsoleContactsView(scanner);

            List<MenuItem> menuItemsList = List.of(new ShowAllMenuItem(contactsService, contactsView),
                    new AddContactMenuItem(contactsService, contactsView),
                    new ExitMenuItem("Exit")
            );
            Menu menu = new Menu(scanner, menuItemsList);
            menu.run();
        }
    }
}
