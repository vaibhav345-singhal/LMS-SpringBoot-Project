package com.FirstSpringBootApplication.LibraryManagementSystem.Entity;

import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @CreationTimestamp
    private Date createdDate;

    @OneToOne
    @JoinColumn
     @JsonIgnore
    Student student;
}
