package com.rmit.advprog.assignment2.controllers;

import java.sql.SQLException;

import com.rmit.advprog.assignment2.util;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DeletePostController extends NavController {

    @FXML
    private Text errorMessage;

    @FXML
    private TextField id;

    @FXML
    void deletePost(MouseEvent event) {
        boolean validInput = true;

        int idInput = util.validateNonNegativeNumber(id.getText());
        if (idInput == -1) {
            errorMessage.setText("Error: ID must be a number");
            validInput = false;
        } if (idInput == -2) {
            errorMessage.setText("Error: ID must be positive");
            validInput = false;
        }

        if (validInput) {
            errorMessage.setText(facade.deletePost(idInput));
        }
    }

    @FXML
    void getPost(MouseEvent event) throws SQLException {
        boolean validInput = true;

        int idInput = util.validateNonNegativeNumber(id.getText());
        if (idInput == -1) {
            errorMessage.setText("Error: ID must be a number");
            validInput = false;
        } if (idInput == -2) {
            errorMessage.setText("Error: ID must be positive");
            validInput = false;
        } if (facade.getPost(idInput) == null) {
            errorMessage.setText("Error: Post does not exist");
            validInput = false;
        }

        if (validInput) {
            try {
                openPostWindow(idInput);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}