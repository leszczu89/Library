package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.domain.borrowingUpdate.BorrowingUpdate;
import com.crud.library.domain.status.Status;
import com.crud.library.domain.status.StatusChanger;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowingRepository;
import com.crud.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DbService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;
    private final ReaderRepository readerRepository;
    private final StatusChanger statusChanger;
    private final BorrowingUpdate borrowingUpdate;

    public void addReader(Reader reader){
        readerRepository.save(reader);
    }
    public void addBook(Book book){
        bookRepository.save(book);
    }

    public BookCopy addBookCopy(BookCopy bookCopy){
        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> returnAvailableCopies(Book book){
        return bookCopyRepository.retrieveBookCopiesWithAvailableStatus(book);
    }

    public Borrowing borrowBook(Borrowing borrowing){
        return borrowingRepository.save(borrowing);
    }

    public Borrowing returnBook(Borrowing borrowing){
        Borrowing updatedBorrowing = borrowingUpdate.updateBorrowing(borrowing);
        borrowingRepository.save(updatedBorrowing);
        BookCopy updatedCopy = statusChanger.updateBookCopyToAvailable(borrowing.getBookCopy());
        bookCopyRepository.save(updatedCopy);
        return updatedBorrowing;
    }

    public void deleteBookCopy(BookCopy bookCopy){
        bookCopyRepository.deleteById(bookCopy.getId());
    }

    public BookCopy getBookCopy(Long id) {
        return bookCopyRepository.getBookCopyById(id);
    }

    public void deleteBookCopy(Long id) {
        bookCopyRepository.deleteById(id);
    }

    public BookCopy changeBookCopyStatusAsBorrowed(BookCopy bookCopy){
        return statusChanger.updateBookCopyToBorrowed(bookCopy);

    }

    public Reader findReader(Long id) {
        return readerRepository.findReaderById(id);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }
}
