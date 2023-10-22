package com.rmit.advprog.assignment2.controllers;

import java.sql.SQLException;

import com.rmit.advprog.assignment2.Post;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PostWindowController extends FxmlController {

    @FXML
    private Text content;

    @FXML
    private Text dateTime;

    @FXML
    private Text id;

    @FXML
    private Text likes;

    @FXML
    private Text shares;

    private int postId;

    public void setPostId(int postId) throws SQLException {
        this.postId = postId;
        loadPostDetails();
    }

    private void loadPostDetails() throws SQLException {
        Post post = facade.getPost(postId);

        id.setText(Integer.toString(post.getId()));
        content.setText(post.getContent());
        likes.setText(Integer.toString(post.getLikes()));
        shares.setText(Integer.toString(post.getShares()));
        dateTime.setText(post.getDate_time());
    }


}
