package com.FirstSpringBootApplication.LibraryManagementSystem.Controller;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.AuthorRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.AuthorResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Author;
import com.FirstSpringBootApplication.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public AuthorResponseDto addAuthor(@RequestBody AuthorRequestDto authorRequestDto) {
        return authorService.addAuthor(authorRequestDto);
    }
}
