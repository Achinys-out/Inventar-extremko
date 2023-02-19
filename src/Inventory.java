package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:8000/inventory";
        String username = "username";
        String password = "password";


        Product product1 = new Product("produkt1", 1, 10.0);
        System.out.printf("NAZOV: " + product1.getName());
        System.out.printf("POPIS: " + product1.getDescription());

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            InventoryDAO inventoryDao = new InventoryDAO(conn);

            while (true) {
                System.out.println("Vyberte akciu:");
                System.out.println("1 - Pridať produkt");
                System.out.println("2 - Editovať existujúci produkt");
                System.out.println("3 - Vymazať produkt");
                System.out.println("4 - Zobraziť všetky produkty");
                System.out.println("5 - Zatvoriť");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Zadajte meno produktu:");
                        String name = input.next();
                        System.out.println("Zadajte kvantitu:");
                        int quantity = input.nextInt();
                        System.out.println("Zadajte cenu:");
                        double price = input.nextDouble();
                        inventoryDao.addProduct(new Product(name, quantity, price));
                        break;

                    case 2:
                        System.out.println("Zadajte ID produktu:");
                        int id = input.nextInt();
                        System.out.println("Zadajte nový počet kusov");
                        quantity = input.nextInt();
                        System.out.println("Zadajte novú cenu:");
                        price = input.nextDouble();
                        inventoryDao.updateProduct(id, quantity, price);
                        break;

                    case 3:
                        System.out.println("Zadajte ID produktu:");
                        id = input.nextInt();
                        inventoryDao.deleteProduct(id);
                        break;

                    case 4:
                        for (Product item : inventoryDao.getAllProducts()) {
                            System.out.println(item);
                        }
                        break;

                    case 5:
                        System.exit(0);

                    default:
                        System.out.println("Nesprávny úkon.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Chyba spojenia s databázou.");
            e.printStackTrace();
        }
    }
}