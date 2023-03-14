package com.FirstSpringBootApplication.LibraryManagementSystem.Service;

import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.LibraryCard;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Student;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Status;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public String addStudent(Student student) {
        // set the value of card
        LibraryCard card = new LibraryCard();
        card.setStatus(Status.ACTIVATED);
        card.setStudent(student);
        card.setValidTill("03/2024");

        // setting card attribute in student
        student.setCard(card);

        studentRepo.save(student); // due to cascade all property it will save card automatically

        return "Student Is Added";
    }
}
