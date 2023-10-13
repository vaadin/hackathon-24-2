package com.example.application.views.helloworld;

import com.example.application.data.User;
import com.example.application.data.UserRepository;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import jakarta.annotation.security.PermitAll;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class HelloWorldView extends VerticalLayout {
    public HelloWorldView(UserRepository userRepository) {
        TextField message = new TextField("Message");

        Grid<User> users = new Grid<User>();
        users.setItems(userRepository.findAll());
        users.addColumn(user -> user.getName());
        users.setSelectionMode(SelectionMode.MULTI);

        users.setHeight("100px");
        users.setWidth("300px");

        add(message, new Text("To"), users, new Button("Send", event -> {
            String text = message.getValue();
            if (text.isBlank()) {
                return;
            }
            
            message.setValue("");
            MainLayout.send(text, users.getSelectedItems());
        }));
    }

}
