package klu.SpringBoot_skill6;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class librarycontroller {
	

	    private List<library> bookList = new ArrayList<>();

	    //welcome
	    @GetMapping("/welcome")
	    public String welcome() {
	        return "Welcome to Online Library System!";
	    }

	    //count
	    @GetMapping("/count")
	    public int totallibrarys() {
	        return 150;
	    }

	    //price
	    @GetMapping("/price")
	    public double samplePrice() {
	        return 499.99;
	    }

	    //books
	    @GetMapping("/books")
	    public List<String> getlibrarys() {
	        List<String> titles = new ArrayList<>();
	        titles.add("book1");
	        titles.add("book2");
	        titles.add("book3");
	        return titles;
	    }

	    //books/{id}
	    @GetMapping("/books/{id}")
	    public library getlibraryById(@PathVariable int id) {
	        return new library(id, "Sample library", "John Doe", 299.99);
	    }

	    //search?title=...
	    @GetMapping("/search")
	    public String searchlibrary(@RequestParam String title) {
	        return "Searching for book: " + title;
	    }

	    // author/{name}
	    @GetMapping("/author/{name}")
	    public String getAuthor(@PathVariable String name) {
	        return "librarys written by author: " + name;
	    }

	    //addbook
	    @PostMapping("/addbook")
	    public String addlibrary(@RequestBody library book) {
	        bookList.add(book);
	        return "library added successfully!";
	    }

	    //viewbooks
	    @GetMapping("/viewbooks")
	    public List<library> viewlibrarys() {
	        return bookList;
	    }
	}
	
	
	

