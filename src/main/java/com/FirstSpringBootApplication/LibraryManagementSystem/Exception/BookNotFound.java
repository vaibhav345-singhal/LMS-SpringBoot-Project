package com.FirstSpringBootApplication.LibraryManagementSystem.Exception;

public class BookNotFound extends Exception {
    public BookNotFound(String msg) {
        super(msg);
    }
}
