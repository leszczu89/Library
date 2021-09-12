package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.domain.status.Status;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowingRepository;
import com.crud.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DbService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;
    private final ReaderRepository readerRepository;

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

    public Borrowing returnBook(Long bookCopyId){
        Borrowing updatedBorrowing = borrowingRepository.findBorrowingsByBookCopy_Id(bookCopyId);
        updatedBorrowing.setReturningDate(LocalDate.now());
        borrowingRepository.save(updatedBorrowing);
        return updatedBorrowing;
    }

    public void deleteBookCopy(BookCopy bookCopy){
        bookCopyRepository.deleteById(bookCopy.getId());
    }

//    public BookCopy getBookCopy(Long id) {
//        return bookCopyRepository.getBookCopyById(id);
//    }
//
//    public void deleteBookCopy(Long id) {
//        bookCopyRepository.deleteById(id);
//    }


    public Reader findReader(Long id) {
        return readerRepository.findReaderById(id);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public BookCopy changeBookCopyStatus(Long bookCopyId, Status status){
        BookCopy bookCopy = bookCopyRepository.getBookCopyById(bookCopyId);
        bookCopy.setStatus(status.getStatus());
        return bookCopyRepository.save(bookCopy);
    }

}
