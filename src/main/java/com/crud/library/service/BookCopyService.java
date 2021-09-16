package com.crud.library.service;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.status.Status;
import com.crud.library.repository.BookCopyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    public BookCopy addBookCopy(BookCopy bookCopy){
        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> returnAvailableCopies(Long bookId){
        return bookCopyRepository.retrieveBookCopiesWithAvailableStatus(bookId);
    }
    public BookCopy changeBookCopyStatus(Long bookCopyId, Status status){
        BookCopy bookCopy = bookCopyRepository.getBookCopyById(bookCopyId);
        bookCopy.setStatus(status.getStatus());
        return bookCopyRepository.save(bookCopy);
    }
    public BookCopy getBookCopyById(Long bookCopyId) {
        return bookCopyRepository.getBookCopyById(bookCopyId);
    }

    public void deleteBookCopyById(Long bookCopyId){
        bookCopyRepository.deleteById(bookCopyId);
    }

}
