package com.crud.library.domain.status;

import com.crud.library.domain.BookCopy;
import org.springframework.stereotype.Service;

@Service
public class StatusChanger {

    public BookCopy updateBookCopyToBorrowed(BookCopy bookCopy){
        return new BookCopy(bookCopy.getId(), bookCopy.getBook(), Status.BORROWED.getStatus());
    }

    public BookCopy updateBookCopyToLost(BookCopy bookCopy){
        return new BookCopy(bookCopy.getId(), bookCopy.getBook(), Status.LOST.getStatus());
    }

    public BookCopy updateBookCopyToAvailable(BookCopy bookCopy){
        return new BookCopy(bookCopy.getId(), bookCopy.getBook(), Status.AVAILABLE.getStatus());
    }
}
