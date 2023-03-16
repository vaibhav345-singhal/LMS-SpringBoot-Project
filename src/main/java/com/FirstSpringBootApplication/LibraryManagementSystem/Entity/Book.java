package com.FirstSpringBootApplication.LibraryManagementSystem.Entity;

import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private int price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private boolean isIssued;
    @ManyToOne
    @JoinColumn
    //@JsonIgnore // to remove null pointer exception because we will encounter it
    Author author;

    @ManyToOne
    @JoinColumn
    LibraryCard card;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<Transaction> transactionList = new ArrayList<>();
}
