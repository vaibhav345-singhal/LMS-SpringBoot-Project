package com.FirstSpringBootApplication.LibraryManagementSystem.Entity;

import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date dob;

    @Enumerated(EnumType.STRING)
    private Department department;

    private String address;
    private String email;

    // to map parent to child
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    LibraryCard card;
}
