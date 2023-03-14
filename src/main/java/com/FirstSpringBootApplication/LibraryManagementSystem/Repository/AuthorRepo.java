package com.FirstSpringBootApplication.LibraryManagementSystem.Repository;

import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
