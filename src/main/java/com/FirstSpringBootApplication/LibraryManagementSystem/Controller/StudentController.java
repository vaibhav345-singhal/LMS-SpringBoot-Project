package com.FirstSpringBootApplication.LibraryManagementSystem.Controller;

import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.StudentRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.StudentResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.StudentUpdateRequestDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.DTO.StudentUpdateResponseDto;
import com.FirstSpringBootApplication.LibraryManagementSystem.Entity.Student;
import com.FirstSpringBootApplication.LibraryManagementSystem.Exception.StudentNotFound;
import com.FirstSpringBootApplication.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public StudentResponseDto addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return studentService.addStudent(studentRequestDto);
    }

    @PutMapping("/updateEmail")
    public ResponseEntity updateStudent(@RequestBody StudentUpdateRequestDto studentUpdateRequestDto) {

        StudentUpdateResponseDto studentUpdateResponseDto;
        try {
            studentUpdateResponseDto = studentService.updateStudent(studentUpdateRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(studentUpdateResponseDto, HttpStatus.OK);
    }

    @GetMapping("/FindByEmail")
    public ResponseEntity getStudentByEmail(@RequestParam("email") String email) {
        Student student;
        try {
            student = studentService.getStudentByEmail(email);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(student, HttpStatus.OK);
    }
}
