package com.FirstSpringBootApplication.LibraryManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponseDto {

    private String name;
    private int id;

    public String toString() {
        return this.name + " is added with id " + this.id;
    }
}
