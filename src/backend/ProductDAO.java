package backend;

import backend.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {
public void addProduct(String category_name) {
    String sql = "INSERT INTO category(category_name) VALUES (?)";
    try (Connection conn = ConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, category_name);

        int rows = ps.executeUpdate();
        System.out.println(rows + " product(s) added.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();  // class holding above methods
        dao.addProduct("Fruits");
    }

  }