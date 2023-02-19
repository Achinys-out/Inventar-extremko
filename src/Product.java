package src;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private int quantity;


    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = addDescription();
    }

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = addDescription();
    }

    private String addDescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vložte nový popis: ");
        return scanner.nextLine();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product other) {
            return this.id == other.id &&
                    this.name.equals(other.name) &&
                    this.quantity == other.quantity &&
                    this.price == other.price &&
                    this.description.equals(other.description);
        }
        return false;
    }

    @Override
    public String toString() {
        return "CENA: " + name +
                "\nID: " + id +
                "\nCENA: " + price +
                "\nPOČET: " + quantity +
                "\n\nPOPIS:\n " + description;
    }

    public static void main(String[] args) {
        System.out.println("NAME: ");
    }
}