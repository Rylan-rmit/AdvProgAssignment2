package com.rmit.advprog.assignment2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    public static List<Post> resultSetToList(ResultSet rs) throws SQLException {
        List<Post> posts = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String content = rs.getString("content");
            String author = rs.getString("author");
            int likes = rs.getInt("likes");
            int shares = rs.getInt("shares");
            String date_time = rs.getString("date_time");

            Post post = new Post(id, content, author, likes, shares, date_time);
            posts.add(post);
        }

        return posts;
    }

    public static Post singleResultToPost(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int id = rs.getInt("ID");
            String content = rs.getString("content");
            String author = rs.getString("author");
            int likes = rs.getInt("likes");
            int shares = rs.getInt("shares");
            String date_time = rs.getString("date_time");

            return new Post(id, content, author, likes, shares, date_time);
        }

        return null;  // Return null if ResultSet is empty
    }
}
