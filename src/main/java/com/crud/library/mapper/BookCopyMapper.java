package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.dto.BookCopyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyMapper {

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        BookMapper bookMapper = new BookMapper();
        return new BookCopy(
                bookCopyDto.getId(),
                bookMapper.mapToBook(bookCopyDto.getBook()),
                bookCopyDto.getStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        BookMapper bookMapper = new BookMapper();
        return new BookCopyDto(
                bookCopy.getId(),
                bookMapper.mapToBookDto(bookCopy.getBook()),
                bookCopy.getStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }
}
