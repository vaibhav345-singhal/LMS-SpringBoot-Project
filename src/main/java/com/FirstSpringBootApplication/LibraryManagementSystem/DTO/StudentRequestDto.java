package com.FirstSpringBootApplication.LibraryManagementSystem.DTO;

import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDto {
    private String name;
    private Date dob;
    private String address;
    private String email;

    private Department department;
}
