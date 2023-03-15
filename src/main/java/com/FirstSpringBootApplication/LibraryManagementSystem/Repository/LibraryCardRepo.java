package com.FirstSpringBootApplication.LibraryManagementSystem.Repository;

import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepo extends JpaRepository<LibraryCard, Integer> {
}
