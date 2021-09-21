package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Borrowing;
import com.crud.library.domain.Reader;
import com.crud.library.domain.status.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowingRepositoryTestSuite {

    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void testAddNewBorrow() {
        //Given
        Book book = new Book();
        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        Reader reader = new Reader();
        bookRepository.save(book);
        Long bookId = book.getId();

        bookCopyRepository.save(bookCopy);
        Long bookCopyId = bookCopy.getId();

        readerRepository.save(reader);
        Long readerId = reader.getId();

        Borrowing borrowing = new Borrowing(bookCopy, reader, LocalDate.now());
        //When
        borrowingRepository.save(borrowing);
        Long borrowId = borrowing.getId();
        //Then
        assertTrue(borrowingRepository.existsById(borrowId));
        //Clean up
        borrowingRepository.delete(borrowing);
        readerRepository.deleteById(readerId);
        bookCopyRepository.deleteById(bookCopyId);
        bookRepository.deleteById(bookId);
    }
}
