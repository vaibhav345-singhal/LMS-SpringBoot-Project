package com.FirstSpringBootApplication.LibraryManagementSystem.DTO;


import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.TransectionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookIssueResponseDto {

    private String title;
    private String transectionNumber;
    private TransectionStatus transectionStatus;
}
