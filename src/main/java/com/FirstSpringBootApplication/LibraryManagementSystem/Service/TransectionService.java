package com.FirstSpringBootApplication.LibraryManagementSystem.Service;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.BookIssueRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.BookIssueResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Book;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.LibraryCard;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Transection;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Status;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.TransectionStatus;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.TransectionType;
import com.FirstSpringBootApplication.LibraryManagementSystem.Exception.BookNotFound;
import com.FirstSpringBootApplication.LibraryManagementSystem.Exception.CardNotFound;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.BookRepo;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.LibraryCardRepo;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.TransectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransectionService {

    @Autowired
    TransectionRepo transectionRepo;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    LibraryCardRepo libraryCardRepo;

    public BookIssueResponseDto issueBook(BookIssueRequestDto bookIssueRequestDto) throws BookNotFound, CardNotFound {
        Book book;
        Transection transection = new Transection();
        transection.setTransectionType(TransectionType.ISSUE);
        transection.setTransectionNumber(UUID.randomUUID().toString());
        try {
            book = bookRepo.findById(bookIssueRequestDto.getBookId()).get();
        } catch (Exception e) {
            transection.setTransectionStatus(TransectionStatus.FAILED);
            transection.setTransectionMessage("Book Not Found");
            transectionRepo.save(transection);
            throw new BookNotFound("Book Not Found");
        }

        LibraryCard card;
        try {
            card = libraryCardRepo.findById(bookIssueRequestDto.getCardId()).get();
        } catch (Exception e) {
            transection.setTransectionStatus(TransectionStatus.FAILED);
            transection.setTransectionMessage("Card Not Found");
            transectionRepo.save(transection);
            throw new CardNotFound("Card Not Found");
        }

        BookIssueResponseDto bookIssueResponseDto = new BookIssueResponseDto();

        if (book.isIssued()) {
            transection.setTransectionStatus(TransectionStatus.FAILED);
            transection.setTransectionMessage("Book is already issued");
            transectionRepo.save(transection);
            bookIssueResponseDto.setTitle(book.getTitle());
            bookIssueResponseDto.setTransectionNumber(transection.getTransectionNumber());
            bookIssueResponseDto.setTransectionStatus(transection.getTransectionStatus());
            return bookIssueResponseDto;
        }

        if (card.getStatus() != Status.ACTIVATED) {
            transection.setTransectionStatus(TransectionStatus.FAILED);
            transection.setTransectionMessage("Card is not Active");
            transectionRepo.save(transection);

            bookIssueResponseDto.setTitle(book.getTitle());
            bookIssueResponseDto.setTransectionNumber(transection.getTransectionNumber());
            bookIssueResponseDto.setTransectionStatus(transection.getTransectionStatus());
            return bookIssueResponseDto;
        }


        transection.setBook(book);
        transection.setCard(card);
        transection.setTransectionStatus(TransectionStatus.SUCCESS);
        transection.setTransectionMessage("Book Is Issued Successfully");


        book.setCard(card);
        book.setIssued(true);
        book.getTransectionList().add(transection);

        card.getBookList().add(book);
        card.getTransectionList().add(transection);

        libraryCardRepo.save(card);

        bookIssueResponseDto = new BookIssueResponseDto(book.getTitle(), transection.getTransectionNumber(), transection.getTransectionStatus());

        return bookIssueResponseDto;
    }
}
