package com.crud.library.mapper;

import com.crud.library.domain.Borrowing;
import com.crud.library.domain.dto.BorrowingDto;
import org.springframework.stereotype.Service;

@Service
public class BorrowingsMapper {

    public Borrowing mapToBorrowing(final BorrowingDto borrowingDto) {
        return new Borrowing(
                borrowingDto.getId(),
                borrowingDto.getBookCopy(),
                borrowingDto.getReader(),
                borrowingDto.getBorrowingDate(),
                borrowingDto.getReturningDate()
        );
    }

    public BorrowingDto mapToBorrowingDto(final Borrowing borrowing) {
        return new BorrowingDto(
                borrowing.getId(),
                borrowing.getBookCopy(),
                borrowing.getReader(),
                borrowing.getBorrowingDate(),
                borrowing.getReturningDate()
        );
    }
}
