package com.rmit.advprog.assignment2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController extends FxmlController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    void login(MouseEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (facade.checkPassword(username, password))
        {
            facade.setCurrentUser(username);
            this.switchScene(event, "main");
        } else {
            System.out.println("Incorrect username or password");
        }
   
    }

    @FXML
    void register(MouseEvent event) {
        this.switchScene(event, "register");
    }

}
