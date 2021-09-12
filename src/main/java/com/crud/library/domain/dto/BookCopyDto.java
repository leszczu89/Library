package com.crud.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCopyDto {

    private Long id;
    private BookDto book;
    private String status;
}
