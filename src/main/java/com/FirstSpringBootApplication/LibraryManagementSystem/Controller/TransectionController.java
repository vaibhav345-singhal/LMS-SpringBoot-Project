package com.FirstSpringBootApplication.LibraryManagementSystem.Controller;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.BookIssueRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.BookIssueResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Service.TransectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transection")
public class TransectionController {

    @Autowired
    TransectionService transectionService;

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody BookIssueRequestDto bookIssueRequestDto) {
        BookIssueResponseDto bookIssueResponseDto;
        try {
            bookIssueResponseDto = transectionService.issueBook(bookIssueRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookIssueResponseDto, HttpStatus.OK);
    }
}
