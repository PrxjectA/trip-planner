package com.tripplanner;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/trip_planner";
        String user = "root";
        String password = "my-secret-pw"; // same as in Docker

        try {
            // Connect to database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL!");

            // Create a table
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS destinations (id INT AUTO_INCREMENT PRIMARY KEY, city VARCHAR(255))");

            // Insert a few cities
            stmt.executeUpdate("INSERT INTO destinations (city) VALUES ('Tokyo'), ('Geneva'), ('Reykjavik')");

            // Read them back out
            ResultSet rs = stmt.executeQuery("SELECT * FROM destinations");

            System.out.println("🌍 Destinations:");
            while (rs.next()) {
                System.out.println("- " + rs.getString("city"));
            }

            // Close connections
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}
