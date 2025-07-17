/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nethula_Rankidu
 */
public class Auth {
    public static User login(String username, String password) {
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");
                String uname = rs.getString("username");
                String fname = rs.getString("full_name");
                
                String updateQuery = "UPDATE admin SET last_login = NOW() WHERE user_id = ?";
                
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, id); // replace with your actual user ID
                    updateStmt.executeUpdate();
                }

                return new User(id, uname, fname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}