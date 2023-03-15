package com.FirstSpringBootApplication.LibraryManagementSystem.Entity;

import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.TransectionStatus;
import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.TransectionType;
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
public class Transection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transectionNumber;

    @Enumerated(EnumType.STRING)
    private TransectionStatus transectionStatus;

    private String transectionMessage;

    @Enumerated(EnumType.STRING)
    private TransectionType transectionType;

    @ManyToOne
    @JoinColumn
    Book book;

    @ManyToOne
    @JoinColumn
    LibraryCard card;

}
