package backend;

import backend.ConnectionManager;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {
public void addProduct(String name, String barcode, BigDecimal price) {
    String sql = "INSERT INTO products(name, barcode, unit_price) VALUES (?, ?, ?)";
    try (Connection conn = ConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, name);
        ps.setString(2, barcode);
        ps.setBigDecimal(3, price);

        int rows = ps.executeUpdate();
        System.out.println(rows + " product(s) added.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void updatePrice(int productId, BigDecimal newPrice) {
    String sql = "UPDATE products SET unit_price = ? WHERE product_id = ?";
    try (Connection conn = ConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setBigDecimal(1, newPrice);
        ps.setInt(2, productId);

        int rows = ps.executeUpdate();
        System.out.println(rows + " product(s) updated.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteProduct(int productId) {
    String sql = "DELETE FROM products WHERE product_id = ?";
    try (Connection conn = ConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, productId);
        int rows = ps.executeUpdate();
        System.out.println(rows + " product(s) deleted.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public Product findProductByBarcode(String barcode) {
    String sql = "SELECT product_id, name, unit_price FROM products WHERE barcode = ?";
    try (Connection conn = ConnectionManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, barcode);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Product(
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getBigDecimal("unit_price")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public static void main(String[] args) {

    }

  }