package com.rmit.advprog.assignment2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class EditProfileController extends NavController {

    @FXML
    private PasswordField currentPassword;

    @FXML
    private Text errorMessage;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField newPassword;

    public void initialize() {
        firstName.setText(facade.getFirstName(facade.getCurrentUser()));
        lastName.setText(facade.getLastName(facade.getCurrentUser()));
    }

    @FXML
    void subscribe(MouseEvent event) {

    }

    @FXML
    void updateProfile(MouseEvent event) {
        String currentPasswordInput = currentPassword.getText();
        String newPasswordInput = newPassword.getText();
        String firstNameInput = firstName.getText();
        String lastNameInput = lastName.getText();

        errorMessage.setText(facade.updateProfile(facade.getCurrentUser(), currentPasswordInput, newPasswordInput, firstNameInput, lastNameInput));
    }

}
