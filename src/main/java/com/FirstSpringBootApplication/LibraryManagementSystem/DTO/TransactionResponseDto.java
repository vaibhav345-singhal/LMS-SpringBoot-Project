package com.FirstSpringBootApplication.LibraryManagementSystem.DTO;


import com.FirstSpringBootApplication.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionResponseDto {

    private String title;
    private String transactionNumber;
    private TransactionStatus transactionStatus;
    private String message;
}
