package com.rmit.advprog.assignment2.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MostLikesEntryController extends NavController {

    @FXML
    private Text errorMessage;

    @FXML
    private TextField n;

    @FXML
    private TextField author;

    @FXML
    void viewPosts(MouseEvent event) {
        facade.setTableAuthorRequest(author.getText());
        facade.setTableNRequest(Integer.parseInt(n.getText()));

        openTableWindow();
    }

    @FXML
    private void openTableWindow() {
        try {
            // Load the FXML layout for the post window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/getMostLikesView.fxml"));
            Parent root = fxmlLoader.load();
    
            // Create a new stage for the post window
            Stage stage = new Stage();
            stage.setTitle("Post Details");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
