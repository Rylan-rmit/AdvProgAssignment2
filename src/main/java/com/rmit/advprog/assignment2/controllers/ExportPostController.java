package com.rmit.advprog.assignment2.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import com.rmit.advprog.assignment2.Post;
import com.rmit.advprog.assignment2.util;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class ExportPostController extends NavController {
    @FXML
    private Text errorMessage;

    @FXML
    private TextField id;

    FileChooser fileChooser = new FileChooser();

    @FXML
    void exportPost(MouseEvent event) {
        Post post = null;
        try {
            post = facade.getPost(Integer.parseInt(id.getText()));
            
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Posts to CSV");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
                );
                
            File file = fileChooser.showSaveDialog(id.getScene().getWindow()); 
            if (file != null) {
                // 2. Write posts to the CSV file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    // Write header (optional)
                    writer.write("ID,Content,Author,Likes,Shares,Date_Time\n");
                    
                    writer.write(post.getId() + "," + 
                    escape(post.getContent()) + "," + 
                    escape(post.getAuthor()) + "," + 
                    post.getLikes() + "," + 
                    post.getShares() + "," + 
                    escape(post.getDate_time()) + "\n");
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            errorMessage.setText("Success: Post exported to CSV");
        } catch (NumberFormatException | SQLException e) {
            errorMessage.setText("Error: ID must be a number");
        }
    }

    private String escape(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
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
