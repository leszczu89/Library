package com.crud.library.domain.borrowingUpdate;

import com.crud.library.domain.Borrowing;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingUpdate {

    public Borrowing updateBorrowing(Borrowing borrowing){
        return new Borrowing(borrowing.getId(), borrowing.getBookCopy(), borrowing.getReader(), borrowing.getBorrowingDate(), LocalDate.now());
    }
}
