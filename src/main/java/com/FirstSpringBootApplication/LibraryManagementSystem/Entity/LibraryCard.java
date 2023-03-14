package com.FirstSpringBootApplication.LibraryManagementSystem.Entity;

import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String validTill;
    @OneToOne
    @JoinColumn
    Student student;
}
