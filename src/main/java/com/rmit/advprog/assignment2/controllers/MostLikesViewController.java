package com.rmit.advprog.assignment2.controllers;

import java.sql.SQLException;
import java.util.List;

import com.rmit.advprog.assignment2.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MostLikesViewController extends NavController {

    @FXML
    private TableView<Post> tableView;
    @FXML
    private TableColumn<Post, Integer> idColumn;
    @FXML
    private TableColumn<Post, Integer> likesColumn;
    @FXML
    private TableColumn<Post, String> contentColumn;
    @FXML
    private TableColumn<Post, Void> viewColumn;

    
    public void initialize() throws SQLException {
        populateTable();
    }

    private void populateTable() throws SQLException {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        likesColumn.setCellValueFactory(cellData -> cellData.getValue().likesProperty().asObject());
        contentColumn.setCellValueFactory(cellData -> cellData.getValue().contentProperty());
        
        viewColumn.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("View Post");

            {
                btn.setOnAction(event -> {
                    Post post = getTableView().getItems().get(getIndex());
                    try {
                        openPostWindow(post.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        List<Post> posts = facade.getTopNPostsByLikes(facade.getTableNRequest(), facade.getTableAuthorRequest());
        tableView.getItems().addAll(posts);
    }
}
