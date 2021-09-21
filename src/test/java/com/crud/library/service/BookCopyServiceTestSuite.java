package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.status.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyServiceTestSuite {
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BookService bookService;

    @Test
    public void testAddBookCopy() {
        //Given
        Book book = new Book("t", "a", LocalDate.of(2002, 5, 6));
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        //When
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();
        //Then
        assertEquals("t", bookCopyService.getBookCopyById(bookCopyId).getBook().getTitle());
        assertEquals("a", bookCopyService.getBookCopyById(bookCopyId).getBook().getAuthor());
        //Clean Up
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }
    @Test
    public void testReturnAvailableCopies() {
        //Given
        Book book = new Book("title", "author", LocalDate.of(2011, 6, 16));
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        BookCopy bookCopy1 = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();
        bookCopyService.addBookCopy(bookCopy1);
        Long bookCopy1Id = bookCopy1.getId();
        //When
        List<BookCopy> bookCopies = bookCopyService.returnAvailableCopies(bookId);
        //Then
        assertEquals(2, bookCopies.size());
        //Clean Up
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookCopyService.deleteBookCopyById(bookCopy1Id);
        bookService.deleteBook(bookId);
    }
    @Test
    public void testChangeBookCopyStatus() {
        //Given
        Book book = new Book("title1", "author1", LocalDate.of(2021, 1, 26));
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();
        //When
        bookCopyService.changeBookCopyStatus(bookCopyId, Status.LOST);
        //Then
        assertEquals("lost", bookCopyService.getBookCopyById(bookCopyId).getStatus());
        //Clean Up
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }
    @Test
    public void testGetBookCopyById(){
        //Given
        Book book = new Book("title2", "author2", LocalDate.of(2021, 1, 26));
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();
        //When
        BookCopy testedCopy = bookCopyService.getBookCopyById(bookCopyId);
        //Then
        assertEquals(bookCopyId, testedCopy.getId());
        assertEquals("available", testedCopy.getStatus());
        //Clean Up
        bookCopyService.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }
    @Test
    public void testDeleteBookCopyById() {
        //Given
        Book book = new Book("title3", "author3", LocalDate.of(2001, 11, 22));
        bookService.addBook(book);
        Long bookId = book.getId();

        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        bookCopyService.addBookCopy(bookCopy);
        Long bookCopyId = bookCopy.getId();
        //When
        bookCopyService.deleteBookCopyById(bookCopyId);
        //Then
        assertNull(bookCopyService.getBookCopyById(bookCopyId));
        //Clean Up
        bookService.deleteBook(bookId);
    }
}
