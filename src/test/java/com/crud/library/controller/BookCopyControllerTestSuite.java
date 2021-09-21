package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyControllerTestSuite {

    @Autowired
    private BookCopyController bookCopyController;
    @Autowired
    private BookService bookService;

    @Test
    void testAddBookCopy(){
        //Given
        Book book = new Book("title", "author", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();
        //When
        BookCopyDto bookCopyDto = bookCopyController.addBookCopy(bookId);
        //Then
        assertEquals("available", bookCopyDto.getStatus());
        assertEquals("title", bookCopyDto.getBook().getTitle());
        assertEquals("author", bookCopyDto.getBook().getAuthor());
        //Clean up
        bookCopyController.deleteBookCopyById(bookCopyDto.getId());
        bookService.deleteBook(bookId);
    }
    @Test
    void testGetAvailableCopies(){
        //Given
        Book book = new Book("title", "author", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();
        BookCopyDto bookCopyDto1 = bookCopyController.addBookCopy(bookId);
        BookCopyDto bookCopyDto2 = bookCopyController.addBookCopy(bookId);
        //When
        List<BookCopyDto> bookCopyDtoList = bookCopyController.getAvailableCopies(bookId);
        //Then
        assertEquals(2, bookCopyDtoList.size());
        assertEquals("available", bookCopyDtoList.get(0).getStatus());
        assertEquals("available", bookCopyDtoList.get(1).getStatus());
        bookCopyController.updateCopyAsLost(bookCopyDto1.getId());
        bookCopyDtoList = bookCopyController.getAvailableCopies(bookId);
        assertEquals(1, bookCopyDtoList.size());
        //Clean up
        bookCopyController.deleteBookCopyById(bookCopyDto1.getId());
        bookCopyController.deleteBookCopyById(bookCopyDto2.getId());
        bookService.deleteBook(bookId);
    }
    @Test
    void testGetBookCopyById(){
        //Given
        Book book = new Book("t", "a", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();
        BookCopyDto bookCopyDto = bookCopyController.addBookCopy(bookId);
        Long bookCopyId = bookCopyDto.getId();
        //When
        BookCopyDto returnedBookCopy = bookCopyController.getBookCopyById(bookCopyId);
        //Then
        assertEquals("t", returnedBookCopy.getBook().getTitle());
        assertEquals("a", returnedBookCopy.getBook().getAuthor());
        assertEquals("available", returnedBookCopy.getStatus());
        assertEquals(bookCopyId, returnedBookCopy.getId());
        //Clean up
        bookCopyController.deleteBookCopyById(bookCopyId);
        bookService.deleteBook(bookId);
    }
    @Test
    void testDeleteBookCopyById(){
        //Given
        Book book = new Book("Book title", "Book author", LocalDate.now());
        bookService.addBook(book);
        Long bookId = book.getId();
        BookCopyDto bookCopyDto = bookCopyController.addBookCopy(bookId);
        Long bookCopyId = bookCopyDto.getId();
        //When
        bookCopyController.deleteBookCopyById(bookCopyId);
        List<BookCopyDto> returnedBookCopies = bookCopyController.getAvailableCopies(bookId);
        //Then
        assertEquals(0,returnedBookCopies.size());
        //Clean up
        bookService.deleteBook(bookId);
    }
}
