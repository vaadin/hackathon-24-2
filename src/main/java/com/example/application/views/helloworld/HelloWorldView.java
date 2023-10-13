package com.example.application.views.helloworld;

import com.example.application.data.User;
import com.example.application.data.UserRepository;
import com.example.application.security.AuthenticatedUser;
import com.example.application.services.PushService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.webpush.WebPush;

import jakarta.annotation.security.PermitAll;
import nl.martijndwars.webpush.Subscription;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class HelloWorldView extends VerticalLayout {
    private final AuthenticatedUser authenticatedUser;
    
    private final Div pushControls = new Div();

    public HelloWorldView(AuthenticatedUser authenticatedUser, UserRepository userRepository) {
        this.authenticatedUser = authenticatedUser;
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
        }), pushControls);
    }
    
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        UI ui = attachEvent.getUI();
        
        WebPush webPush = MainLayout.pushService.getWebPush();
        webPush.subscriptionExists(ui, exists -> {
            if (!exists) {
                showSubscribeButton(ui, webPush);
            } else {
                webPush.fetchExistingSubscription(ui, subscription -> {
                    MainLayout.pushService.register(
                            authenticatedUser.get().get().getUsername(),
                            subscription);
                    
                    showUnsubscribeButton(ui, webPush, subscription);
                });
            }
        });
    }

    private void showUnsubscribeButton(UI ui, WebPush webPush, Subscription subscription) {
        pushControls.removeAll();
        pushControls.add(new Button("Unsubscribe", event -> {
            MainLayout.pushService.unregister(authenticatedUser.get().get().getUsername(), subscription);
            event.getSource().setText("Unsubscribing");
            event.getSource().setEnabled(false);
            webPush.unsubscribe(ui, ignore -> {
                showSubscribeButton(ui, webPush);
            });
        }));
    }

    private void showSubscribeButton(UI ui, WebPush webPush) {
        pushControls.removeAll();
        pushControls.add(new Button("Subscribe to push notifications", e -> {
            e.getSource().setText("Subscribing");
            e.getSource().setEnabled(false);
            webPush.subscribe(ui, subscription -> {
                MainLayout.pushService.register(
                        authenticatedUser.get().get().getUsername(),
                        subscription);
                showUnsubscribeButton(ui, webPush, subscription);
            });
        }));
    }
    
    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        
        pushControls.removeAll();
    }

}
