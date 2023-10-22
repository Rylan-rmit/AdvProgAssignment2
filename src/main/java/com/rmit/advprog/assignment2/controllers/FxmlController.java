package com.rmit.advprog.assignment2.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rmit.advprog.assignment2.Facade;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxmlController {

    @FXML
    protected AnchorPane ap;

    @FXML
    protected BorderPane bp;

    protected Facade facade = Facade.getInstance();

    protected void switchScene(MouseEvent event, String scene) {
        try {
            // Load the FXML file.
            Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/" + scene + ".fxml"));

            // Create a new scene with the loaded root.
            Scene newScene = new Scene(newRoot);

            // Get the current stage.
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the current stage.
            currentStage.setScene(newScene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void loadPage(String page) {
        Parent root = null;
        
        try {
            URL pagePath = getClass().getResource("/fxml/" + page + ".fxml");
            root = FXMLLoader.load(pagePath);
        } catch (IOException e) {
            Logger.getLogger(NavController.class.getName()).log(Level.SEVERE, null, e);
        }

        bp.setCenter(root);
    }

}
