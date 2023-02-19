package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/inventory";
        String username = "your-username";
        String password = "your-password";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            InventoryDAO inventoryDao = new InventoryDAO(conn);

            System.out.println("Welcome to the Inventory Manager!");
            while (true) {
                System.out.println("What would you like to do?");
                System.out.println("1. Add a new item");
                System.out.println("2. Update an existing item");
                System.out.println("3. Delete an item");
                System.out.println("4. View all items");
                System.out.println("5. Exit");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter the item name:");
                        String name = input.next();
                        System.out.println("Enter the quantity:");
                        int quantity = input.nextInt();
                        System.out.println("Enter the price:");
                        double price = input.nextDouble();
                        inventoryDao.addProduct(new Product(name, quantity, price));
                        break;

                    case 2:
                        System.out.println("Enter the item ID:");
                        int id = input.nextInt();
                        System.out.println("Enter the new quantity:");
                        quantity = input.nextInt();
                        System.out.println("Enter the new price:");
                        price = input.nextDouble();
                        inventoryDao.updateProduct(id, quantity, price);
                        break;

                    case 3:
                        System.out.println("Enter the item ID:");
                        id = input.nextInt();
                        inventoryDao.deleteProduct(id);
                        break;

                    case 4:
                        for (Product item : inventoryDao.getAllProducts()) {
                            System.out.println(item);
                        }
                        break;

                    case 5:
                        System.out.println("Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database.");
            e.printStackTrace();
        }
    }
}