package com.FirstSpringBootApplication.LibraryManagementSystem.Controller;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.TransactionRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.TransactionResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transection")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto transactionResponseDto;
        try {
            transactionResponseDto = transactionService.issueBook(transactionRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(transactionResponseDto, HttpStatus.OK);
    }

    @PutMapping("/return")
    public ResponseEntity returnBook(@RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto transactionResponseDto;
        try {
            transactionResponseDto = transactionService.returnBook(transactionRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(transactionResponseDto, HttpStatus.OK);
    }
}
