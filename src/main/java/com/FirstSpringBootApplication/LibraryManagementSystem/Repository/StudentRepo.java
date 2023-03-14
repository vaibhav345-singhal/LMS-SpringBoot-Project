package com.FirstSpringBootApplication.LibraryManagementSystem.Repository;

import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
