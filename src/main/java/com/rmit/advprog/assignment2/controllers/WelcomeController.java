package com.rmit.advprog.assignment2.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class WelcomeController extends NavController implements Initializable {
    
    @FXML
    private Text welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeText.setText("Welcome " + facade.getFirstName(facade.getCurrentUser()) + " " + facade.getLastName(facade.getCurrentUser()));
    }
}