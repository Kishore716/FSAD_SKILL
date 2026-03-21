package klu.Skill8;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Derived Query Methods
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    // JPQL Query - Sort by price
    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> sortByPrice();

    // JPQL Query - Products above price
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findExpensiveProducts(double price);

    // JPQL Query - Category
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> getByCategory(String category);
}