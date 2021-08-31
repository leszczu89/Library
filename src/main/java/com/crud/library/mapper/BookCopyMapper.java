package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.dto.BookCopyDto;
import org.springframework.stereotype.Service;

@Service
public class BookCopyMapper {

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getBook(),
                bookCopyDto.getStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getBook(),
                bookCopy.getStatus()
        );
    }
}
