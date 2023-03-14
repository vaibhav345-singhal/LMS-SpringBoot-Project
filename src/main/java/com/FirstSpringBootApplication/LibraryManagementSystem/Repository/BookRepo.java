package com.FirstSpringBootApplication.LibraryManagementSystem.Repository;

import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
}
