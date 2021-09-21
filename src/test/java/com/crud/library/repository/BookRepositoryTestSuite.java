package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testAddBook() {
        //Given
        Book book = new Book("a", "b", LocalDate.now());

        //When
        bookRepository.save(book);
        Long bookId = book.getId();

        //Then
        assertTrue(bookRepository.existsById(bookId));

        //Clean up
        bookRepository.deleteById(bookId);

    }
    @Test
    void testFindAllBooks(){
        //Given
        Book book1 = new Book("first", "first", LocalDate.now());
        Book book2 = new Book("second", "second", LocalDate.now());
        //When
        bookRepository.save(book1);
        Long book1Id = book1.getId();
        bookRepository.save(book2);
        Long books2Id = book2.getId();
        //Then
        List<Book> bookList = bookRepository.findAll();
        assertEquals(2, bookList.size());
        //Clean Up
        bookRepository.deleteById(book1Id);
        bookRepository.deleteById(books2Id);
    }
}
