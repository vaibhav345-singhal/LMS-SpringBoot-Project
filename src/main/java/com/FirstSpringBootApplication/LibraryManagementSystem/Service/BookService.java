package com.FirstSpringBootApplication.LibraryManagementSystem.Service;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.AddBookRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.AddBookResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Author;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Book;
import com.FirstSpringBootApplication.LibraryManagementSystem.Exception.AuthorNotFound;
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

    public AddBookResponseDto addBook(AddBookRequestDto addBookRequestDto) throws AuthorNotFound {

        Author author;
        try {
            author = authorRepo.findById(addBookRequestDto.getAuthorId()).get();
        } catch (Exception e) {
            throw new AuthorNotFound("Author Not Found");
        }

        Book book = new Book();
        book.setGenre(addBookRequestDto.getGenre());
        book.setPrice(addBookRequestDto.getPrice());
        book.setTitle(addBookRequestDto.getTitle());
        book.setAuthor(author);

        author.getBook().add(book);

        authorRepo.save(author);

        AddBookResponseDto addBookResponseDto = new AddBookResponseDto(book.getTitle(), book.getPrice(), book.getGenre());
        return addBookResponseDto;
    }

    public List<Book> getBook() {

        return bookRepo.findAll();
    }
}
