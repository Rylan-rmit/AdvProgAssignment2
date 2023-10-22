package com.rmit.advprog.assignment2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class RegisterController extends FxmlController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Text errorMessage;

    @FXML
    void back(MouseEvent event) {
        this.switchScene(event, "login");
    }

    @FXML
    void register(MouseEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();

        if (password.equals(repeatPassword)) {
            if (facade.registerUser(username, password, firstName, lastName)) {
                this.switchScene(event, "login");
            } else {
                errorMessage.setText("Error: Username already exists");
            }
        } else {
            errorMessage.setText("Error: Passwords do not match");
        }
    }

}
