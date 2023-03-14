package com.FirstSpringBootApplication.LibraryManagementSystem.Service;

import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Author;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Book;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.AuthorRepo;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    AuthorRepo authorRepo;

    public String addBook(Book book) {
        Author author;

        try {
            author = authorRepo.findById(book.getAuthor().getId()).get(); // to check if its present or not
        } catch (Exception e) {
            return e.getMessage();
        }

        List<Book> bookWritten = author.getBook();
        bookWritten.add(book);

        authorRepo.save(author);
        return "Book Added";
    }

    public List<Book> getBook() {

        return bookRepo.findAll();
    }
}
