package backend;

import backend.StockBatchItem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductDAO {
    // Adding Products
    /*public void addProduct(String name, String barcode, Integer price) {
        String sql = "INSERT INTO products(product_name, product_barcode, category_id) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, barcode);
            ps.setInt(3, price);

            int rows = ps.executeUpdate();
            System.out.println(rows + " product(s) added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    // Update code to be used later
    /*public void updatePrice(int productId, BigDecimal newPrice) {
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
    }*/

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
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addCategory(String cat_name) {
        if (cat_name == null || cat_name.trim().isEmpty()) {
            System.out.println("Category name cannot be empty.");
            JOptionPane.showMessageDialog(null, "Category name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String checkSql = "SELECT COUNT(*) FROM category WHERE category_name = ?";
        String insertSql = "INSERT INTO category(category_name) VALUES (?)";

        try (Connection conn = ConnectionManager.getConnection()) {

            // Step 1: Check if the category already exists
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, cat_name.trim());
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    System.out.println("Category already exists.");
                    JOptionPane.showMessageDialog(null, "Category already exists.", "Duplicate", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Step 2: Insert the new category
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, cat_name.trim());
                int rows = insertStmt.executeUpdate();

                if (rows > 0) {
                    ResultSet keys = insertStmt.getGeneratedKeys();
                    if (keys.next()) {
                        int catId = keys.getInt(1);
                        System.out.println("Category Successfully Added");
                        JOptionPane.showMessageDialog(null, "Category Successfully Added!\nID: " + catId + "\nName: " + cat_name.trim(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Category ID: " + catId);
                        System.out.println("Category Name: " + cat_name.trim());
                    }
                } else {
                    System.out.println("No category was added. Something went wrong.");
                    JOptionPane.showMessageDialog(null, "No category was added. Something went wrong.", "Insert Failed", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addProduct(String productName, String barcode, int category) {
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Product name cannot be empty.");
            JOptionPane.showMessageDialog(null, "Product name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (barcode == null || barcode.trim().isEmpty()) {
            System.out.println("Barcode cannot be empty.");
            JOptionPane.showMessageDialog(null, "Barcode cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (category == 0) {
            System.out.println("Please Select a Category");
            JOptionPane.showMessageDialog(null, "Please Select a Category", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String checkSql = "SELECT COUNT(*) FROM products WHERE product_barcode = ?";
        String insertSql = "INSERT INTO products(product_name, product_barcode, category_id) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection()) {

            // Step 1: Check if the category already exists
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, barcode.trim());
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    System.out.println("Product with the same barcode already exists.");
                    JOptionPane.showMessageDialog(null, "Product with the same barcode already exists.", "Duplicate", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Step 2: Insert the new category
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, productName.trim());
                insertStmt.setString(2, barcode.trim());
                insertStmt.setString(3, category + "");

                int rows = insertStmt.executeUpdate();

                if (rows > 0) {
                    ResultSet keys = insertStmt.getGeneratedKeys();
                    if (keys.next()) {
                        int catId = keys.getInt(1);
                        System.out.println("Category Successfully Added");
                        JOptionPane.showMessageDialog(null, "Category Successfully Added!\nID: " + catId + "\nName: " + productName.trim(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Product ID: " + catId);
                        System.out.println("Product Name: " + productName.trim());
                    }
                } else {
                    System.out.println("No product was added. Something went wrong.");
                    JOptionPane.showMessageDialog(null, "No product was added. Something went wrong.", "Insert Failed", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addCustomer(String cusName, String phoneNumber, String email, String birthYear, int gender) {
        if (cusName == null || cusName.trim().isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            JOptionPane.showMessageDialog(null, "Customer name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (gender == 0) {
            System.out.println("Please Select a Gender / Atleast Other");
            JOptionPane.showMessageDialog(null, "Please Select a Gender / Atleast Other", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(birthYear.trim().isEmpty()) {
            birthYear = "1970";
        } else if (isInteger(birthYear) == false) {
            JOptionPane.showMessageDialog(null, "Please insert a valid number for Birth Year", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String insertSql = "INSERT INTO customer(name, phone, email, birth_year, gender_gender_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection()) {
            // Step 2: Insert the new category
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, cusName.trim());
                insertStmt.setString(2, phoneNumber.trim());
                insertStmt.setString(3, email.trim());
                insertStmt.setInt(4, Integer.parseInt(birthYear));
                insertStmt.setString(5, gender + "");

                int rows = insertStmt.executeUpdate();

                if (rows > 0) {
                    ResultSet keys = insertStmt.getGeneratedKeys();
                    if (keys.next()) {
                        int catId = keys.getInt(1);
                        System.out.println("Customer Successfully Added");
                        JOptionPane.showMessageDialog(null, "Customer Successfully Added!\nID: " + catId + "\nName: " + cusName.trim(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Product ID: " + catId);
                        System.out.println("Product Name: " + cusName.trim());
                    }
                } else {
                    System.out.println("No product was added. Something went wrong.");
                    JOptionPane.showMessageDialog(null, "No product was added. Something went wrong.", "Insert Failed", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addStock(int productId, int units, String receivedDate, String expireDate, double productPrice, double productCost) {
        if (productId == 0) {
            System.out.println("Please select a product");
            JOptionPane.showMessageDialog(null, "Please select a product", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (units <= 0) {
            System.out.println("Units cannot be empty or negative");
            JOptionPane.showMessageDialog(null, "Units cannot be empty or negative", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (receivedDate == null || receivedDate.trim().isEmpty()) {
            System.out.println("Please select a received date");
            JOptionPane.showMessageDialog(null, "Please select a received date", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (expireDate == null || expireDate.trim().isEmpty()) {
            System.out.println("Please select a expire date");
            JOptionPane.showMessageDialog(null, "Please select a expire date", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (productPrice <= 0) {
            System.out.println("Product price cannot be empty or negative");
            JOptionPane.showMessageDialog(null, "Product price cannot be empty or negative", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (productCost <= 0) {
            System.out.println("Product cost cannot be empty or negative");
            JOptionPane.showMessageDialog(null, "Product price cannot be empty or negative", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String insertSql = "INSERT INTO product_batches(product_id, units, expiry_date, received_date, product_cost, product_price, remaining_items, bought_items) " + 
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionManager.getConnection()) {

            // Step 2: Insert the new category
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, productId + "");
                insertStmt.setString(2, units + "");
                insertStmt.setString(3, expireDate);
                insertStmt.setString(4, receivedDate);
                insertStmt.setString(5, productCost + "");
                insertStmt.setString(6, productPrice + "");
                insertStmt.setString(7, units + "");
                insertStmt.setString(8, "0");

                int rows = insertStmt.executeUpdate();

                if (rows > 0) {
                    ResultSet keys = insertStmt.getGeneratedKeys();
                    if (keys.next()) {
                        int catId = keys.getInt(1);
                        System.out.println("Stock Successfully Added");
                        JOptionPane.showMessageDialog(null, "Stock Successfully Added!\nStock ID: " + catId + "\nProduct ID: " + productId, "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Stock ID: " + catId);
                        System.out.println("Product ID: " + productId);
                    }
                } else {
                    System.out.println("No Stock was added. Something went wrong.");
                    JOptionPane.showMessageDialog(null, "No Stock was added. Something went wrong.", "Insert Failed", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public Integer getProductIdByBarcode(String barcode) {
        if (barcode == null || barcode.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Barcode cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String sql = "SELECT product_id FROM products WHERE product_barcode = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, barcode.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("product_id");
            } else {
                JOptionPane.showMessageDialog(null, "Product not found.", "Warning", JOptionPane.WARNING_MESSAGE);
                return null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public List<StockBatchItem> getStockBatchesByProductId(int productId) {
        List<StockBatchItem> batches = new ArrayList<>();
        String sql = "SELECT batch_id, expiry_date, remaining_items FROM product_batches WHERE product_id = ? AND remaining_items > 0";

        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int batchId = rs.getInt("batch_id");
                Date expiry = rs.getDate("expiry_date");
                int remaining = rs.getInt("remaining_items");
                String label = "Batch " + batchId + " | Exp: " + expiry + " | Qty: " + remaining;
                batches.add(new StockBatchItem(batchId, label, remaining));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading stock batches: " + e.getMessage());
        }

        return batches;
    }
    
    public class StockBatchDetails {
        public String productName;
        public double unitPrice;
        public String stockDate;  // Could also be java.sql.Date
        public int remainingItems;
        public int productId;

        public StockBatchDetails(String productName, double unitPrice, String stockDate, int remainingItems, int productId) {
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.stockDate = stockDate;
            this.remainingItems = remainingItems;
            this.productId = productId;
        }
    }
    
    public StockBatchDetails getStockBatchDetailsById(int batchId) {
        String sql = """
            SELECT p.product_name, b.product_price, b.received_date, b.remaining_items, p.product_id
            FROM product_batches b
            JOIN products p ON b.product_id = p.product_id
            WHERE b.batch_id = ?
        """;

        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, batchId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("product_name");
                double price = rs.getDouble("product_price");
                Date receivedDate = rs.getDate("received_date");
                int remaining = rs.getInt("remaining_items");
                int productId = rs.getInt("product_id");

                return new StockBatchDetails(name, price, receivedDate.toString(), remaining, productId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching batch details: " + e.getMessage());
        }

        return null;
    }
}