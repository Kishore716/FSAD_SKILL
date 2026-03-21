package klu.Skill8;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository pr;

    @PostMapping
    public Product addProduct(@RequestBody Product p) {
        return pr.save(p);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return pr.findByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> filter(@RequestParam double min, @RequestParam double max) {
        return pr.findByPriceBetween(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> sortedProducts() {
        return pr.sortByPrice();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> expensiveProducts(@PathVariable double price) {
        return pr.findExpensiveProducts(price);
    }
}