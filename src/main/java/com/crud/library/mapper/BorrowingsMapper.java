package com.crud.library.mapper;

import com.crud.library.domain.Borrowing;
import com.crud.library.domain.dto.BorrowingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingsMapper {

    public Borrowing mapToBorrowing(final BorrowingDto borrowingDto) {
       BookCopyMapper bookCopyMapper = new BookCopyMapper();
       ReaderMapper readerMapper = new ReaderMapper();
        return new Borrowing(
                borrowingDto.getId(),
                bookCopyMapper.mapToBookCopy(borrowingDto.getBookCopy()),
                readerMapper.mapToReader(borrowingDto.getReader()),
                borrowingDto.getBorrowingDate(),
                borrowingDto.getReturningDate()
        );
    }

    public BorrowingDto mapToBorrowingDto(final Borrowing borrowing) {
        BookCopyMapper bookCopyMapper = new BookCopyMapper();
        ReaderMapper readerMapper = new ReaderMapper();
        return new BorrowingDto(
                borrowing.getId(),
                bookCopyMapper.mapToBookCopyDto(borrowing.getBookCopy()),
                readerMapper.mapToReaderDto(borrowing.getReader()),
                borrowing.getBorrowingDate(),
                borrowing.getReturningDate()
        );
    }
}
