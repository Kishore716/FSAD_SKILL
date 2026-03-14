package klu.Hibernate_crud_hql_;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

@Entity
@Table(name = "products")
class product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private double price;
    private int quantity;

    public product() {}

    public product(String name, String description, double price, int quantity) {
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

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description +
                ", price=" + price + ", quantity=" + quantity + "]";
    }
}

public class Product {

    public static void main(String[] args) {

        // 1️⃣ Configure Hibernate
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // Make sure hibernate.cfg.xml exists
                .addAnnotatedClass(product.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            // 2️⃣ Add 5–8 Product Records
            session.beginTransaction();

            session.save(new product("Laptop", "Electronics", 60000, 10));
            session.save(new product("Mobile", "Electronics", 30000, 15));
            session.save(new product("Chair", "Furniture", 2500, 20));
            session.save(new product("Table", "Furniture", 4500, 5));
            session.save(new product("Pen", "Stationery", 20, 100));
            session.save(new product("Notebook", "Stationery", 80, 50));
            session.save(new product("Headphone", "Electronics", 2500, 8));
            session.save(new product("Cupboard", "Furniture", 15000, 2));

            session.getTransaction().commit();

            // 3️⃣ HQL Queries – Sorting
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\n-- Products by Price Ascending --");
            List<product> list = session.createQuery("from Product p order by p.price asc", Product.class).list();
            list.forEach(System.out::println);

            System.out.println("\n-- Products by Price Descending --");
            list = session.createQuery("from Product p order by p.price desc", Product.class).list();
            list.forEach(System.out::println);

            System.out.println("\n-- Products by Quantity Descending --");
            list = session.createQuery("from Product p order by p.quantity desc", Product.class).list();
            list.forEach(System.out::println);

            // 5️⃣ Pagination
            System.out.println("\n-- First 3 Products --");
            Query<product> query = session.createQuery("from Product", Product.class);
            query.setFirstResult(0);
            query.setMaxResults(3);
            query.list().forEach(System.out::println);

            System.out.println("\n-- Next 3 Products --");
            query.setFirstResult(3);
            query.setMaxResults(3);
            query.list().forEach(System.out::println);

            // 6️⃣ Aggregate functions
            Long totalProducts = session.createQuery("select count(p) from Product p", Long.class).getSingleResult();
            System.out.println("\nTotal Products: " + totalProducts);

            Long quantityPositive = session.createQuery("select count(p) from Product p where p.quantity > 0", Long.class)
                    .getSingleResult();
            System.out.println("Products with quantity > 0: " + quantityPositive);

            System.out.println("\nProducts count grouped by description:");
            List<Object[]> groupList = session.createQuery(
                    "select p.description, count(p) from Product p group by p.description", Object[].class).list();
            groupList.forEach(obj -> System.out.println(obj[0] + " : " + obj[1]));

            Object[] minMax = session.createQuery("select min(p.price), max(p.price) from Product p", Object[].class)
                    .getSingleResult();
            System.out.println("Min Price: " + minMax[0] + ", Max Price: " + minMax[1]);

            // 7️⃣ GROUP BY Description
            System.out.println("\n-- GROUP BY Description --");
            List<Object[]> groupByDesc = session.createQuery(
                    "select p.description, p from Product p group by p.description", Object[].class).list();
            groupByDesc.forEach(obj -> System.out.println(obj[0] + " -> " + obj[1]));

            // 8️⃣ WHERE clause – Price range filter
            System.out.println("\n-- Products with price between 1000 and 50000 --");
            query = session.createQuery("from Product p where p.price between :min and :max", Product.class);
            query.setParameter("min", 1000.0);
            query.setParameter("max", 50000.0);
            query.list().forEach(System.out::println);

            // 9️⃣ LIKE Queries
            System.out.println("\n-- Names starting with 'C' --");
            session.createQuery("from Product p where p.name like 'C%'", Product.class).list().forEach(System.out::println);

            System.out.println("\n-- Names ending with 'e' --");
            session.createQuery("from Product p where p.name like '%e'", Product.class).list().forEach(System.out::println);

            System.out.println("\n-- Names containing 'top' --");
            session.createQuery("from Product p where p.name like '%top%'", Product.class).list().forEach(System.out::println);

            System.out.println("\n-- Names with exact 5 characters --");
            session.createQuery("from Product p where length(p.name) = 5", Product.class).list().forEach(System.out::println);

            session.getTransaction().commit();

            System.out.println("\n✅ Hibernate HQL Lab Completed Successfully!");

        } finally {
            factory.close();
        }
    }
}
