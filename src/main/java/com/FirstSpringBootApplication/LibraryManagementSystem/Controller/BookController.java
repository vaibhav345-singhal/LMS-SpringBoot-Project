package com.FirstSpringBootApplication.LibraryManagementSystem.Controller;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.AddBookRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.AddBookResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Author;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Book;
import com.FirstSpringBootApplication.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequestDto addBookRequestDto) {
        AddBookResponseDto addBookResponseDto;
        try {
            addBookResponseDto = bookService.addBook(addBookRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(addBookResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get")
    public List<Book> getBook() {
        return bookService.getBook();
    }
}
