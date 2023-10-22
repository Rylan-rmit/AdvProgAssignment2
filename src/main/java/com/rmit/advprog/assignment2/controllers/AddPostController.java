package com.rmit.advprog.assignment2.controllers;

import com.rmit.advprog.assignment2.util;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AddPostController extends NavController {

    @FXML
    private TextArea content;

    @FXML
    private TextField dateTime;

    @FXML
    private Text errorMessage;

    @FXML
    private TextField id;

    @FXML
    private TextField likes;

    @FXML
    private TextField shares;

    @FXML
    private TextField author;

    @FXML
    void addPost(MouseEvent event) {
        boolean validInput = true;

        int idInput = util.validateNonNegativeNumber(id.getText());
        if (idInput == -1) {
            errorMessage.setText("Error: ID must be a number");
            validInput = false;
        } if (idInput == -2) {
            errorMessage.setText("Error: ID must be positive");
            validInput = false;
        } if (facade.idExists(idInput)) {
            errorMessage.setText("Error: ID already exists");
            validInput = false;
        }


        String contentInput = util.validateString(this.content.getText());
        if (contentInput == null) {
            errorMessage.setText("Error: Content cannot be empty");
            validInput = false;
        }

        String authorInput = util.validateString(this.author.getText());
        if (authorInput == null) {
            errorMessage.setText("Error: Author cannot be empty");
            validInput = false;
        }

        int likesInput = util.validateNonNegativeNumber(likes.getText());
        if (likesInput == -1) {
            errorMessage.setText("Error: Likes must be a number");
            validInput = false;
        } if (likesInput == -2) {
            errorMessage.setText("Error: Likes must be positive");
            validInput = false;
        }


        int sharesInput = util.validateNonNegativeNumber(shares.getText());
        if (sharesInput == -1) {
            errorMessage.setText("Error: Shares must be a number");
            validInput = false;
        } if (sharesInput == -2) {
            errorMessage.setText("Error: Shares must be positive");
            validInput = false;
        } 

        String dateTime = this.dateTime.getText();
        if (!util.isValidDate(dateTime)) {
            errorMessage.setText("Error: Invalid date format");
            validInput = false;
        }

        System.out.println(validInput);

        if (validInput) {
            facade.addPost(idInput, contentInput, authorInput, likesInput, sharesInput, dateTime);
            try {
                openPostWindow(idInput);
                loadPage("addPost");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}