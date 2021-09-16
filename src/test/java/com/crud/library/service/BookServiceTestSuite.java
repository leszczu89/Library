package com.crud.library.service;

import com.crud.library.domain.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class BookServiceTestSuite {
    @Autowired
    private BookService bookService;

    @Test
    public void addBookTest() {
        //Given
        Book book = new Book("title", "author", LocalDate.of(2000, 2, 3));
        //When
        bookService.addBook(book);
        Long bookId = book.getId();
        //Then
        assertEquals("author", bookService.getBook(bookId).getAuthor());
        assertEquals("title", bookService.getBook(bookId).getTitle());
        assertEquals(LocalDate.of(2000, 2, 3), bookService.getBook(bookId).getPublicationDate());
        //Clean Up
        try{
            bookService.deleteBook(bookId);
        } catch (Exception e) {

        }
    }
    @Test
    public void getBookTest() {
        //Given
        Book book = new Book("t", "a", LocalDate.of(2020, 4, 12));
        bookService.addBook(book);
        Long bookId = book.getId();
        //When
        Book examineBook = bookService.getBook(bookId);
        //Then
        assertEquals(bookId, examineBook.getId());
        assertEquals("a", examineBook.getAuthor());
        assertEquals("t", examineBook.getTitle());
        //Clean Up
        bookService.deleteBook(bookId);
    }
    @Test
    public void deleteBookTest(){
        //Given
        Book book = new Book("title1", "author1", LocalDate.of(2021, 12, 4));
        bookService.addBook(book);
        Long bookId = book.getId();
        //When
        bookService.deleteBook(bookId);
        //Then
        assertEquals(Optional.empty(), bookService.getBookById(bookId));
    }
}
