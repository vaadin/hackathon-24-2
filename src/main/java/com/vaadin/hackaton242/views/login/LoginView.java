package com.vaadin.hackaton242.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.hackaton242.views.MainLayout;
import com.vaadin.hackaton242.views.about.AboutView;

@PageTitle("Login")
@Route(value = "login", layout = MainLayout.class)
public class LoginView extends VerticalLayout {

    public LoginView() {

        var loginOverlay = new LoginOverlay();

        var integerField = new IntegerField("One time password");
        integerField.getElement().setAttribute("name", "otp");
        loginOverlay.getCustomFormArea().add(integerField);
        integerField.setRequired(true);

        add(loginOverlay);

        loginOverlay.setOpened(true);


        loginOverlay.addLoginListener(loginEvent -> {
            var username = loginEvent.getUsername();
            var password = loginEvent.getPassword();
            var oneTimePassword = integerField.getValue();


            integerField.setInvalid(oneTimePassword == null);
            if (oneTimePassword == null) {
                integerField.setErrorMessage("Must not be empty");
                loginOverlay.setEnabled(true);
                return;
            }
            System.out.printf("User: %s, Password: %s, OTP: %d%n", username, password, oneTimePassword);
            UI.getCurrent().navigate(AboutView.class);
        });

    }
}
