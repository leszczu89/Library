package com.crud.library.controller;

import com.crud.library.domain.dto.BookDto;
import com.crud.library.service.BookCopyService;
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
public class BookControllerTestSuite {

    @Autowired
    private BookController bookController;
    @Autowired
    private BookService bookService;

    @Test
    public void testAddBook(){
        //Given
        BookDto bookDto = new BookDto("t", "a", LocalDate.now());

        //When
        BookDto addedBook = bookController.addBook(bookDto);
        Long bookId = addedBook.getId();
        //Then
        assertEquals("a", addedBook.getAuthor());
        assertEquals("t", addedBook.getTitle());
        //Clean Up
        bookService.deleteBook(bookId);
    }
    @Test
    public void testGetBookById(){
        //Given
        BookDto bookDto = new BookDto("title", "author", LocalDate.now());
        BookDto addedBook = bookController.addBook(bookDto);
        Long bookId = addedBook.getId();
        //When
        BookDto returnedBook = bookController.getBook(bookId);
        //Then
        assertEquals("title", returnedBook.getTitle());
        assertEquals("author", returnedBook.getAuthor());
        //Clean up
        bookService.deleteBook(bookId);
    }
    @Test
    void testDeleteBookById(){
        //Given
        BookDto bookDto = new BookDto("Book title", "Book author", LocalDate.now());
        BookDto addedBook = bookController.addBook(bookDto);
        Long bookId = addedBook.getId();
        //When
        bookController.deleteBook(bookId);
        List<BookDto> bookList = bookController.getAllBooks();
        //Then
        assertEquals(0, bookList.size());
    }
    @Test
    void testGetAllBooks(){
        //Given
        BookDto bookDto1 = new BookDto("Book title1", "Book author1", LocalDate.now());
        bookController.addBook(bookDto1);
        BookDto bookDto2 = new BookDto("Book title2", "Book author2", LocalDate.now());
        bookController.addBook(bookDto2);
        //When
        List<BookDto> bookList = bookController.getAllBooks();
        //Then
        assertEquals(2, bookList.size());
        assertEquals("Book title1", bookList.get(0).getTitle());
        assertEquals("Book title2", bookList.get(1).getTitle());
        //Clean up
        bookService.deleteBook(bookList.get(1).getId());
        bookService.deleteBook(bookList.get(0).getId());
    }

}
