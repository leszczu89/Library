package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.BorrowingDto;
import com.crud.library.domain.status.Status;
import com.crud.library.service.BookCopyService;
import com.crud.library.service.BookService;
import com.crud.library.service.ReaderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowingControllerTestSuite {
    @Autowired
    private BorrowingController borrowingController;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private ReaderService readerService;

    @Test
    void testAddBorrowing(){
        //Given
        Book book = new Book("title", "author", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();

        Reader reader = new Reader("name", "lastname");
        readerService.saveReader(reader);
        Long readerId = reader.getId();

        //When
        BorrowingDto borrowingDto = borrowingController.addBorrowing(bookCopyId, readerId);
        //Then
        assertEquals("name", borrowingDto.getReader().getName());
        assertEquals("title", borrowingDto.getBookCopy().getBook().getTitle());
        assertEquals(Status.BORROWED.getStatus(), borrowingDto.getBookCopy().getStatus());
        //Clean up
        borrowingController.deleteBorrowing(borrowingDto.getId());
        readerService.deleteReader(readerId);
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }
    @Test
    void testBookReturn(){
        //Given
        Book book = new Book("t", "a", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();

        Reader reader = new Reader("n", "l");
        readerService.saveReader(reader);
        Long readerId = reader.getId();

        BorrowingDto borrowingDto = borrowingController.addBorrowing(bookCopyId, readerId);

        //When and Then
        assertEquals(Status.BORROWED.getStatus(), borrowingDto.getBookCopy().getStatus());
        BorrowingDto returned = borrowingController.bookReturn(bookCopyId);
        assertEquals(Status.AVAILABLE.getStatus(), returned.getBookCopy().getStatus());
        //Clean up
        borrowingController.deleteBorrowing(borrowingDto.getId());
        readerService.deleteReader(readerId);
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }
}
