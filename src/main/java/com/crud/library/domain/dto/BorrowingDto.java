package com.crud.library.domain.dto;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BorrowingDto {

    private Long id;
    private BookCopy bookCopy;
    private Reader reader;
    private LocalDate borrowingDate;
    private LocalDate returningDate;
}
