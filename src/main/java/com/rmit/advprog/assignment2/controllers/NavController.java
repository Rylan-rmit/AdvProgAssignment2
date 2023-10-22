package com.rmit.advprog.assignment2.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NavController extends FxmlController {

    @FXML
    void home(MouseEvent event) {
        bp.setCenter(ap);
    }
    
    @FXML
    void addPost(MouseEvent event) {
        loadPage("addPost");
    }
    
    @FXML
    void viewPost(MouseEvent event) {
        loadPage("getPost");
    }

    @FXML
    void deletePost(MouseEvent event) {
        loadPage("deletePost");
    }

    @FXML
    void mostLiked(MouseEvent event) {
        loadPage("getMostLikesEntry");
    }

    @FXML
    void exportPost(MouseEvent event) {
        loadPage("exportPost");
    }

    @FXML
    void editProfile(MouseEvent event) {
        loadPage("editProfile");
    }

    @FXML
    void logOut(MouseEvent event) {
        switchScene(event, "login");
    }
    
    @FXML
    protected void openPostWindow(int postId) throws SQLException {
        try {
            // Load the FXML layout for the post window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/postWindow.fxml"));
            Parent root = fxmlLoader.load();
    
            // Retrieve the controller and call the setPostId method
            PostWindowController postWindowController = fxmlLoader.getController();
            postWindowController.setPostId(postId);
    
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
