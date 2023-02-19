package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class InventoryDAO {
    private Connection conn;

    public InventoryDAO(Connection conn) {
        this.conn = conn;
    }

    public void addProduct(Product item) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO inventar (name, quantity, price) VALUES (?, ?, ?, ?)");
        statement.setString(1, item.getName());
        statement.setInt(2, item.getQuantity());
        statement.setDouble(3, item.getPrice());
        statement.executeUpdate();
    }

    public void updateProduct(int id, int quantity, double price) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE inventar SET quantity=?, price=? WHERE id=?");
        statement.setInt(1, quantity);
        statement.setDouble(2, price);
        statement.setInt(3, id);
        statement.executeUpdate();
    }

    public void deleteProduct(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM inventar WHERE id=?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> items = new ArrayList<>();
        ResultSet resultSet = (ResultSet) conn.createStatement();
        return items;
    }
}