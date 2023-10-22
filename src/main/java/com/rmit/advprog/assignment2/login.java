package com.rmit.advprog.assignment2;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.SQLException;

public class login extends Application {

    Facade dbInterface = new Facade();
    Stage primaryStage;
    String loginUsername;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginScreen();
        
    }

    private void showLoginScreen(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Create username label and field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);

        // Create password label and field
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);

        // Create login button
        Button loginButton = new Button("Login");
        gridPane.add(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            // Call your database method to validate the user
            if (dbInterface.checkPassword(username, password)) {
                this.loginUsername = username;
                showUserDashboard(username);
            } else {
                Label loginMessage = new Label("Incorrect username or password!");
                gridPane.add(loginMessage, 1, 3, 2, 1);
            }
        });

        Button registerButton = new Button("Register");
        gridPane.add(registerButton, 2, 2);
        registerButton.setOnAction(e -> showRegistrationScreen());

        // Set the scene with the layout
        Scene loginScene = new Scene(gridPane, 300, 150);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Data Analytics Hub - Login");
        primaryStage.show();
    }

    private void showRegistrationScreen() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
    
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
    
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
    
        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordField = new PasswordField();
        gridPane.add(confirmPasswordLabel, 0, 2);
        gridPane.add(confirmPasswordField, 1, 2);
    
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        gridPane.add(firstNameLabel, 0, 3);
        gridPane.add(firstNameField, 1, 3);

        Label lastNameLabel = new Label("LastName:");
        TextField lastNameField = new TextField();
        gridPane.add(lastNameLabel, 0, 4);
        gridPane.add(lastNameField, 1, 4);

        Button registerButton = new Button("Register");
        gridPane.add(registerButton, 1, 5);
    
        Label registrationMessage = new Label();
        gridPane.add(registrationMessage, 1, 6);
    
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String confirmPassword = confirmPasswordField.getText();
    
            if (!password.equals(confirmPassword)) {
                registrationMessage.setText("Passwords do not match!");
                return;
            }
    
            if (dbInterface.registerUser(username, password, firstName, lastName)) {
                registrationMessage.setText("Registered Successfully!");
                showLoginScreen();
            } else {
                registrationMessage.setText("Registration Failed!");
            }
        });
    
        Scene registrationScene = new Scene(gridPane, 400, 200);
        primaryStage.setTitle("Data Analytics Hub - Register");
        primaryStage.setScene(registrationScene);
        primaryStage.show();
    }
    


    private void showUserDashboard(String username) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
    
        // Display a welcome message to the user (you'll need a method to get the full name of the user from the database)
        String fullName = dbInterface.getFirstName(username); 
        Label welcomeLabel = new Label("Welcome, " + fullName + "!");
        layout.getChildren().add(welcomeLabel);
    
        // Buttons for various actions
        Button editProfileButton = new Button("Edit Profile");
        editProfileButton.setOnAction(e -> {
            showEditProfileScreen();
        });
    
        Button addPostButton = new Button("Add a Post");
        addPostButton.setOnAction(e -> {
            showAddPostScreen();
        });
    
        Button retrievePostButton = new Button("Retrieve a Post by ID");
        retrievePostButton.setOnAction(e -> {
            showRetrievePostByIdScreen();
        });
    
        Button removePostButton = new Button("Remove a Post by ID");
        removePostButton.setOnAction(e -> {
            // Implement action to remove a post by ID
        });
    
        Button topNPostsButton = new Button("Top N Posts by Likes");
        topNPostsButton.setOnAction(e -> {
            // Implement action to retrieve top N posts by likes
        });
    
        Button exportPostButton = new Button("Export Post to CSV");
        exportPostButton.setOnAction(e -> {
            // Implement action to export a post to CSV
        });
    
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            showLoginScreen();
        });
    
        // Add all buttons to the layout
        layout.getChildren().addAll(editProfileButton, addPostButton, retrievePostButton, removePostButton, topNPostsButton, exportPostButton, logoutButton);
    
        Scene dashboardScene = new Scene(layout, 400, 500);
        primaryStage.setTitle("User Dashboard");
        primaryStage.setScene(dashboardScene);
        primaryStage.show();
    }


    private void showEditProfileScreen() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        
        // Current Password
        Label currentPasswordLabel = new Label("Current Password:");
        PasswordField currentPasswordField = new PasswordField();
        gridPane.add(currentPasswordLabel, 0, 0);
        gridPane.add(currentPasswordField, 1, 0);
        
        // New Password
        Label newPasswordLabel = new Label("New Password:");
        PasswordField newPasswordField = new PasswordField();
        gridPane.add(newPasswordLabel, 0, 1);
        gridPane.add(newPasswordField, 1, 1);
        
        // First Name
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        firstNameField.setText(dbInterface.getFirstName(loginUsername)); // assuming the method is available in the interface
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameField, 1, 2);
    
        // Last Name
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        // For demonstration: assuming that the method is available in the interface
        // lastNameField.setText(dbInterface.getLastName(loginUsername)); 
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameField, 1, 3);
    
        // Subscribe to Premium
        Button subscribeButton = new Button("Subscribe to Premium");
        gridPane.add(subscribeButton, 0, 4, 2, 1);
        subscribeButton.setOnAction(e -> {
            // Implement subscription logic here
        });
    
        Label updateProfileMessage = new Label();
        gridPane.add(updateProfileMessage, 1, 6);

        // Update Profile Button
        Button updateProfileButton = new Button("Update Profile");
        gridPane.add(updateProfileButton, 1, 5);
        updateProfileButton.setOnAction(e -> {
            // Implement logic to update user profile
            String currentPassword = currentPasswordField.getText();
            String newPassword = newPasswordField.getText();
            String newFirstName = firstNameField.getText();
            String newLastName = lastNameField.getText();
            
            // Update user details in the database
            updateProfileMessage.setText(dbInterface.updateProfile(loginUsername, currentPassword, newPassword, newFirstName, newLastName));
        });
    
        // Back Button
        Button backButton = new Button("Back");
        gridPane.add(backButton, 0, 5);
        backButton.setOnAction(e -> showUserDashboard(loginUsername));
    
        Scene editProfileScene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Data Analytics Hub - Edit Profile");
        primaryStage.setScene(editProfileScene);
        primaryStage.show();
    }
    

    private void showAddPostScreen() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showUserDashboard(loginUsername));
        gridPane.add(backButton, 0, 8);
    
        Label idLabel = new Label("Post ID:");
        TextField idField = new TextField();
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
    
        Label contentLabel = new Label("Content:");
        TextField contentField = new TextField();
        gridPane.add(contentLabel, 0, 1);
        gridPane.add(contentField, 1, 1);
    
        Label authorLabel = new Label("Author:");
        TextField authorField = new TextField();
        gridPane.add(authorLabel, 0, 2);
        gridPane.add(authorField, 1, 2);
    
        Label likesLabel = new Label("Likes:");
        TextField likesField = new TextField();
        gridPane.add(likesLabel, 0, 3);
        gridPane.add(likesField, 1, 3);
    
        Label sharesLabel = new Label("Shares:");
        TextField sharesField = new TextField();
        gridPane.add(sharesLabel, 0, 4);
        gridPane.add(sharesField, 1, 4);
    
        Label dateTimeLabel = new Label("Date:");
        TextField dateTimeField = new TextField();
        gridPane.add(dateTimeLabel, 0, 5);
        gridPane.add(dateTimeField, 1, 5);
    
        Button addButton = new Button("Add Post");
        gridPane.add(addButton, 1, 6);
    
        Label addPostMessage = new Label();
        gridPane.add(addPostMessage, 1, 7, 2, 1);
    
        addButton.setOnAction(e -> {
            // Validate and add post to database (assuming dbInterface has an addPost method)
            addPostMessage.setText(dbInterface.addPost(
                Integer.parseInt(idField.getText()),
                contentField.getText(),
                authorField.getText(),
                Integer.parseInt(likesField.getText()),
                Integer.parseInt(sharesField.getText()),
                dateTimeField.getText()));
        });
    
        Scene addPostScene = new Scene(gridPane, 400, 400);
        primaryStage.setTitle("Data Analytics Hub - Add Post");
        primaryStage.setScene(addPostScene);
        primaryStage.show();
    }
    

    private void showRetrievePostByIdScreen() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showUserDashboard(loginUsername));
        gridPane.add(backButton, 0, 8);
    
        Label idLabel = new Label("Post ID:");
        TextField idField = new TextField();
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
    
        Button retrieveButton = new Button("Retrieve Post");
        gridPane.add(retrieveButton, 1, 1);
    
        Label postDetails = new Label();
        gridPane.add(postDetails, 1, 2, 2, 1);
    
        retrieveButton.setOnAction(e -> {
            try {
                // Retrieve and display post from database
                Post post = dbInterface.getPost(Integer.parseInt(idField.getText()));
                if (post != null) {
                    showRetrievedPost(post);
                } else {
                    postDetails.setText("No post found with the provided ID.");
                }
            } catch (SQLException ex) {
                postDetails.setText("Error fetching post. Please try again.");
                // Optionally, you can print the error for debugging:
                ex.printStackTrace();
            }
        });
    
        Scene retrievePostScene = new Scene(gridPane, 400, 400);
        primaryStage.setTitle("Data Analytics Hub - Retrieve Post by ID");
        primaryStage.setScene(retrievePostScene);
        primaryStage.show();
    }

    private void showRetrievedPost(Post post) {
        Stage postStage = new Stage();
    
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        
        Label postIdLabel = new Label("Post ID: " + post.getId());
        Label contentLabel = new Label("Content: " + post.getContent());
        Label authorLabel = new Label("Author: " + post.getAuthor());
        Label likesLabel = new Label("Likes: " + post.getLikes());
        Label sharesLabel = new Label("Shares: " + post.getShares());
        Label dateTimeLabel = new Label("Date & Time: " + post.getDate_time());
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> postStage.close());
    
        layout.getChildren().addAll(postIdLabel, contentLabel, authorLabel, likesLabel, sharesLabel, dateTimeLabel, backButton);
    
        Scene postScene = new Scene(layout, 400, 300);
        postStage.setScene(postScene);
        postStage.setTitle("Post Details");
        postStage.show();
    }
    
    

    public static void mains(String[] args) {
        launch(args);
    }
}

