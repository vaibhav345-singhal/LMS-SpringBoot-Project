package com.FirstSpringBootApplication.LibraryManagementSystem.Service;

import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Author;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepo authorRepo;

    public String addAuthor(Author author) {
        authorRepo.save(author);
        return "Author Saved";
    }
}
