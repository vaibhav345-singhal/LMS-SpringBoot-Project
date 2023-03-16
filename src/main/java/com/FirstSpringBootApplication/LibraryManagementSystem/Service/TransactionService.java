package com.FirstSpringBootApplication.LibraryManagementSystem.Service;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.TransactionRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.TransactionResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Book;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.LibraryCard;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Transaction;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Status;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.TransactionStatus;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.TransactionType;
import com.FirstSpringBootApplication.LibraryManagementSystem.Exception.BookNotFound;
import com.FirstSpringBootApplication.LibraryManagementSystem.Exception.CardNotFound;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.BookRepo;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.LibraryCardRepo;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    LibraryCardRepo libraryCardRepo;

    @Autowired
    private JavaMailSender emailSender;

    public TransactionResponseDto issueBook(TransactionRequestDto transactionRequestDto) throws BookNotFound, CardNotFound {
        Book book;
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.ISSUE);
        transaction.setTransactionNumber(UUID.randomUUID().toString());
        try {
            book = bookRepo.findById(transactionRequestDto.getBookId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Book Not Found");
            transactionRepo.save(transaction);
            throw new BookNotFound("Book Not Found");
        }

        LibraryCard card;
        try {
            card = libraryCardRepo.findById(transactionRequestDto.getCardId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Card Not Found");
            transactionRepo.save(transaction);
            throw new CardNotFound("Card Not Found");
        }

        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();

        if (book.isIssued()) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Book is already issued");
            transactionRepo.save(transaction);
            transactionResponseDto.setTitle(book.getTitle());
            transactionResponseDto.setTransactionNumber(transaction.getTransactionNumber());
            transactionResponseDto.setTransactionStatus(transaction.getTransactionStatus());
            transactionResponseDto.setMessage("Book is already issued");
            return transactionResponseDto;
        }

        if (card.getStatus() != Status.ACTIVATED) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Card is not Active");
            transactionRepo.save(transaction);

            transactionResponseDto.setTitle(book.getTitle());
            transactionResponseDto.setTransactionNumber(transaction.getTransactionNumber());
            transactionResponseDto.setTransactionStatus(transaction.getTransactionStatus());
            transactionResponseDto.setMessage("card is not active");
            return transactionResponseDto;
        }


        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionMessage("Book Is Issued Successfully");


        book.setCard(card);
        book.setIssued(true);
        book.getTransactionList().add(transaction);

        card.getBookList().add(book);
        card.getTransactionList().add(transaction);

        libraryCardRepo.save(card);

        transactionResponseDto = new TransactionResponseDto(book.getTitle(), transaction.getTransactionNumber(), transaction.getTransactionStatus(), "Book is issued");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jams56smith@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Book Issue Notification");
        message.setText("Dear " + card.getStudent().getName() + " your book with title " + book.getTitle() + " is issued successfully");
        emailSender.send(message);

        return transactionResponseDto;
    }

    public TransactionResponseDto returnBook(TransactionRequestDto transactionRequestDto) throws BookNotFound, CardNotFound {
        Book book;
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.RETURN);
        transaction.setTransactionNumber(UUID.randomUUID().toString());

        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();

        try {
            book = bookRepo.findById(transactionRequestDto.getBookId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Book Not Found");
            transactionRepo.save(transaction);
            throw new BookNotFound("Book not Found");
        }
        LibraryCard card;

        try {
            card = libraryCardRepo.findById(transactionRequestDto.getCardId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Book Not Found");
            transactionRepo.save(transaction);
            throw new CardNotFound("Card Not Found");
        }

        if (!book.isIssued()) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Book Is Not Issued To Given card");
            transactionRepo.save(transaction);
            transactionResponseDto.setTitle(book.getTitle());
            transactionResponseDto.setTransactionStatus(transaction.getTransactionStatus());
            transactionResponseDto.setTransactionNumber(transaction.getTransactionNumber());
            transactionResponseDto.setMessage("Book is Not Issued to given card");
            return transactionResponseDto;
        }

        if (card.getStatus() == Status.DEACTIVATED) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Card is Not Active");
            transactionRepo.save(transaction);
            transactionResponseDto.setTitle(book.getTitle());
            transactionResponseDto.setTransactionStatus(transaction.getTransactionStatus());
            transactionResponseDto.setTransactionNumber(transaction.getTransactionNumber());
            transactionResponseDto.setMessage("Card is not active");
            return transactionResponseDto;
        }

        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionMessage("Book is Returned");

        book.setIssued(false);
        book.getTransactionList().add(transaction);
        book.setCard(null);

        card.getBookList().add(book);
        card.getTransactionList().add(transaction);

        libraryCardRepo.save(card);

        transactionResponseDto = new TransactionResponseDto(book.getTitle(), transaction.getTransactionNumber(), transaction.getTransactionStatus(), "Book is Returned");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jams56smith@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Book Return Notification");
        message.setText("Dear " + card.getStudent().getName() + " your book with title " + book.getTitle() + " is returned successfully");
        emailSender.send(message);

        return transactionResponseDto;
    }
}
