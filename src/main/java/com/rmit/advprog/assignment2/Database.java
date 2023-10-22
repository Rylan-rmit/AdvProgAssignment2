package com.rmit.advprog.assignment2;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class Database {

    private URL dbUrl = getClass().getResource("/Posts.db");
    String jdbcUrl = "jdbc:sqlite:" + dbUrl.getPath();
    private static Connection conn;

    public Database() {
        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean idExists(int id) {
        String sql = "SELECT ID FROM post WHERE ID=?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // Returns true if a result is found, otherwise false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addPost(int id, String content, String author, int likes, int shares, String date_time) {
        String sql = "INSERT INTO post(ID, content, author, likes, shares, date_time) VALUES(?,?,?,?,?,?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, content);
            pstmt.setString(3, author);
            pstmt.setInt(4, likes);
            pstmt.setInt(5, shares);
            pstmt.setString(6, date_time);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getPostById(int id) {
        String sql = "SELECT * FROM post WHERE ID=?";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean removePostById(int id) {
        String sql = "DELETE FROM post WHERE ID=?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getTopNPostsByLikes(int n, String user) {
        String sql;
        if (user.equals("ALL")) {
            sql = "SELECT * FROM post ORDER BY likes DESC LIMIT ?";
        } else {
            sql = "SELECT * FROM post WHERE author=? ORDER BY likes DESC LIMIT ?";
        }
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (!user.equals("ALL")) {
                pstmt.setString(1, user);
                pstmt.setInt(2, n);
            } else {
                pstmt.setInt(1, n);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean exportPostToCSV(int id, String filePath) {
        ResultSet rs = getPostById(id);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("ID,Content,Author,Likes,Shares,DateTime\n");
            while (rs.next()) {
                writer.append(rs.getInt("ID") + ",");
                writer.append(rs.getString("content") + ",");
                writer.append(rs.getString("author") + ",");
                writer.append(rs.getInt("likes") + ",");
                writer.append(rs.getInt("shares") + ",");
                writer.append(rs.getString("date_time") + "\n");
            }
            writer.flush();
            return true;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addUser(String username, String password, String firstName, String lastName) {
        try (
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (username, password, first_name, last_name) VALUES (?, ?, ?, ?)")) {
            
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
    
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public boolean verifyPassword(String username, String passwordToCheck) {
        try (
             PreparedStatement stmt = conn.prepareStatement("SELECT password FROM user WHERE username=?")) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return BCrypt.checkpw(passwordToCheck, storedHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean usernameExists(String username) {
        try (
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM user WHERE username=?")) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
    
            return rs.next(); // If there's a result, it means the username exists
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getFirstName(String username) {
        String sql = "SELECT first_name FROM user WHERE username=?";
        
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("first_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;  // Return null if user not found or in case of an error
    }

    public String getLastName(String username) {
        String sql = "SELECT last_name FROM user WHERE username=?";
        
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("last_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;  // Return null if user not found or in case of an error
    }

    public boolean updateUserProfile(String username, String password, String firstName, String lastName) {
        String sql = "UPDATE user SET password = ?, first_name = ?, last_name = ? WHERE username = ?";
        
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String hashhedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            
            pstmt.setString(1, hashhedPassword);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, username);
            
            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
