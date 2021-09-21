package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Borrowing;
import com.crud.library.domain.Reader;
import com.crud.library.domain.status.Status;
import com.crud.library.exception.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowingServiceTestSuite {
    @Autowired
    private BorrowingService borrowingService;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;

    @Test
    public void testBorrowBook() {
        //Given
        Book book = new Book("title", "author", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();

        Reader reader = new Reader("Jan", "Kowalski");
        readerService.saveReader(reader);
        Long readerId = reader.getId();

        Borrowing borrowing = new Borrowing(bookCopy, reader, LocalDate.now());


        //When
        Borrowing testedBorrowing = borrowingService.borrowBook(borrowing);
        Long borrowingId = borrowing.getId();
        //Then
        assertEquals(bookCopyId, testedBorrowing.getBookCopy().getId());
        assertEquals(borrowingId, testedBorrowing.getId());
        assertEquals(readerId, testedBorrowing.getReader().getId());
        assertEquals(bookId, testedBorrowing.getBookCopy().getBook().getId());
        //Clean Up
        borrowingService.deleteBorrowingById(borrowingId);
        readerService.deleteReader(readerId);
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }
    @Test
    public void testReturnBook(){
        //Given
        Book book = new Book("t", "a", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();

        Reader reader = new Reader("Janusz", "Nowak");
        readerService.saveReader(reader);
        Long readerId = reader.getId();

        Borrowing borrowing = new Borrowing(bookCopy, reader, LocalDate.now());
        borrowingService.borrowBook(borrowing);
        Long borrowingId = borrowing.getId();
        //When

        Borrowing returnedBook = borrowingService.returnBook(bookCopyId);
        //Then
        //assertEquals(Status.BORROWED.getStatus(), borrowedBook.getBookCopy().getStatus());
        assertEquals(Status.AVAILABLE.getStatus(), returnedBook.getBookCopy().getStatus());

        //CleanUp
        borrowingService.deleteBorrowingById(borrowingId);
        readerService.deleteReader(readerId);
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }
    @Test
    public void testDeleteBorrowingById(){
        //Given
        Book book = new Book("title", "author", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();

        Reader reader = new Reader("Jan", "Kowalski");
        readerService.saveReader(reader);
        Long readerId = reader.getId();

        Borrowing borrowing = new Borrowing(bookCopy, reader, LocalDate.now());
        borrowingService.borrowBook(borrowing);
        Long borrowingId = borrowing.getId();
        //When
        borrowingService.deleteBorrowingById(borrowingId);
        //Then
        assertThrows(NoSuchElementException.class, ()-> borrowingService.getBorrowingById(borrowingId));
        //Clean Up
        readerService.deleteReader(readerId);
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }

}
