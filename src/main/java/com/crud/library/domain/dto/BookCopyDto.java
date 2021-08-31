package com.crud.library.domain.dto;

import com.crud.library.domain.Book;
import com.crud.library.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCopyDto {

    private Long id;
    private Book book;
    private Status status;
}
