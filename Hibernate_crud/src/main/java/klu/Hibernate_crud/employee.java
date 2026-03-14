package klu.Hibernate_crud;

import jakarta.persistence.*;

@Entity
//@Table(name = "product")
@Table(name = "products")
@Access(AccessType.FIELD)
public class employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                 // Primary Key

    @Column(name = "name", nullable = false)
    private String name;            // Product name

    @Column(name = "description")
    private String description;     // Product description

    @Column(name = "price")
    private double price;           // Product price

    @Column(name = "quantity")
    private int quantity;           // Product quantity

    // Default constructor (required by Hibernate)
    public employee() {}

    // Convenience constructor
    public employee(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}