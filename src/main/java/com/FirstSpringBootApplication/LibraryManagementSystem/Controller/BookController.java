package com.FirstSpringBootApplication.LibraryManagementSystem.Controller;

import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Author;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Book;
import com.FirstSpringBootApplication.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Book Added";
    }

    @GetMapping("/get")
    public List<Book> getBook(){
        return bookService.getBook();
    }
}
