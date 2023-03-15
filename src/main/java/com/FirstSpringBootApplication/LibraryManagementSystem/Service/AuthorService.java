package com.FirstSpringBootApplication.LibraryManagementSystem.Service;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.AuthorRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.AuthorResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Author;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepo authorRepo;

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {

        Author author = new Author();
        author.setName(authorRequestDto.getName());
        author.setEmail(authorRequestDto.getEmail());

        Author savedAuthor = authorRepo.save(author);

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(savedAuthor.getId());
        authorResponseDto.setName(savedAuthor.getName());

        return authorResponseDto;
    }
}
