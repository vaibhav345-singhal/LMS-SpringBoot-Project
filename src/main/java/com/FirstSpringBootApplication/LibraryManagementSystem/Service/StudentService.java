package com.FirstSpringBootApplication.LibraryManagementSystem.Service;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.StudentRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.StudentResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.StudentUpdateRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.StudentUpdateResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.LibraryCard;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Student;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Status;
import com.FirstSpringBootApplication.LibraryManagementSystem.Exception.StudentNotFound;
import com.FirstSpringBootApplication.LibraryManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public StudentResponseDto addStudent(StudentRequestDto studentRequestDto) {

        // our repository understands only student, so we will make student here
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setDob(studentRequestDto.getDob());
        student.setAddress(studentRequestDto.getAddress());
        student.setDepartment(studentRequestDto.getDepartment());

        LibraryCard card = new LibraryCard();
        card.setStatus(Status.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);

        Student savedStudent = studentRepo.save(student);

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setName(savedStudent.getName());
        studentResponseDto.setId(savedStudent.getId());
        return studentResponseDto;
    }

    public StudentUpdateResponseDto updateStudent(StudentUpdateRequestDto studentUpdateRequestDto) throws StudentNotFound {
        Student student;
        try {
            student = studentRepo.findById(studentUpdateRequestDto.getId()).get();
        } catch (Exception e) {
            throw new StudentNotFound("Student Not Found");
        }

        student.setEmail(studentUpdateRequestDto.getEmail());
        Student savedStudent = studentRepo.save(student);

        StudentUpdateResponseDto studentUpdateResponseDto = new StudentUpdateResponseDto("Updated Email is " + savedStudent.getEmail());
        return studentUpdateResponseDto;
    }

    public Student getStudentByEmail(String email) throws StudentNotFound {
        Student student;
        try {
            student = studentRepo.findByEmail(email);
        } catch (Exception e) {
            throw new StudentNotFound("Student Not Found");
        }
        return student;
    }
}
