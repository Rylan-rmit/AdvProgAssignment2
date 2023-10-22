package com.rmit.advprog.assignment2;

import java.sql.SQLException;
import java.util.List;

public class Facade {
    
    private String currentUser;

    private int tableNRequest = 5;
    private String tableAuthorRequest = "ALL";

    public int getTableNRequest() {
        return tableNRequest;
    }

    public void setTableNRequest(int tableNRequest) {
        this.tableNRequest = tableNRequest;
    }

    public void setTableAuthorRequest(String tableAuthorRequest) {
        this.tableAuthorRequest = tableAuthorRequest;
    }

    public String getTableAuthorRequest() {
        return tableAuthorRequest;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    //Ensure that there is only a single of the interface running at any one time
    private static Facade instance;

    public static Facade getInstance() {
        if (instance == null) {
           instance = new Facade();
        }
        return instance;
    }

    public Facade() {
        db = new Database();
    }
 
    private Database db;
     
    public synchronized boolean idExists(int id) {
        return db.idExists(id);
    }

    public synchronized boolean registerUser(String username, String password, String firstName, String lastName) {
        if (username == null || username.trim().isEmpty() || username.length() > 100) { // Assuming a max length of 100 characters
            return false;
        }
        if (password == null || password.trim().isEmpty() || password.length() > 100) { // Assuming a max length of 100 characters
            return false;
        }

        if(db.usernameExists(username)) {
            return false;
        }

        db.addUser(username, password, firstName, lastName);

        return true;
    }

    public synchronized String getFirstName(String username) {
        return db.getFirstName(username);
    }    

    public synchronized String getLastName(String username) {
        return db.getLastName(username);
    }    

    public synchronized boolean checkPassword(String username, String password) {
        return db.verifyPassword(username, password);
    }

    public synchronized String addPost(int id, String content, String author, int likes, int shares, String date_time) {
        // Check if ID already exists
        if (idExists(id)) {
            return "ID already exists";
        }
    
        // Validate content
        if (content == null || content.trim().isEmpty() || content.length() > 1000) { // Assuming a max length of 1000 characters
            return "Invalid content";
        }
    
        // Validate author
        if (author == null || author.trim().isEmpty() || author.length() > 100) { // Assuming a max length of 100 characters
            return "Invalid author";
        }
    
        // Validate likes and shares
        if (likes < 0 || shares < 0) {
            return "Likes or shares cannot be negative";
        }
    
        // Validate date_time format
        if (!util.isValidDate(date_time)) {
            return "Invalid date-time format";
        }
    
        // Add post
        if (this.db.addPost(id, content, author, likes, shares, date_time)) {
            return "Post added successfully";
        } else {
            return "Failed to add post";
        }
    }

    public synchronized String updateProfile(String username, String oldPassword, String newPassword, String firstName, String lastName){
        if (username == null || username.trim().isEmpty() || username.length() > 100) { // Assuming a max length of 100 characters
            return "Invalid username";
        }
        System.out.println(oldPassword);

        if (!this.checkPassword(username, oldPassword)) { // Assuming a max length of 100 characters
            return "Invalid current password";
        }

        if (newPassword == null || newPassword.trim().isEmpty() || newPassword.length() > 100) { // Assuming a max length of 100 characters
            return "Invalid new password";
        }

        if (firstName == null || firstName.trim().isEmpty() || firstName.length() > 100) { // Assuming a max length of 100 characters
            return "Invalid first name";
        }

        if (lastName == null || lastName.trim().isEmpty() || lastName.length() > 100) { // Assuming a max length of 100 characters
            return "Invalid last name";
        }
        if (this.db.updateUserProfile(username, newPassword, firstName, lastName)) {
            return "Profile updated successfully";
        } else {
            return "Failed to update profile";
        }
    }  

    

    public synchronized String deletePost(int id) {
        if (this.db.removePostById(id)) {
            return "Post deleted successfully";
        } else {
            return "Failed to delete post";
        }
    }

    public synchronized Post getPost(int id) throws SQLException {
        return DatabaseUtil.singleResultToPost(this.db.getPostById(id));
    }

    public synchronized List<Post> getTopNPostsByLikes(int n, String user) throws SQLException {
        return DatabaseUtil.resultSetToList(this.db.getTopNPostsByLikes(n, user));
    }

}
