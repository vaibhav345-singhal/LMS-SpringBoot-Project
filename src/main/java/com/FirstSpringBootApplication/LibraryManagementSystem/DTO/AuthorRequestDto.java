package com.FirstSpringBootApplication.LibraryManagementSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorRequestDto {
    private String name;
    private String email;
}
