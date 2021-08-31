package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookRepositoryTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void addBookTest() {
        //Given
        Book book = new Book();

        //When
        bookRepository.save(book);
        Long bookId = book.getId();

        //Then
        assertTrue(bookRepository.existsById(bookId));

        //Clean up
        bookRepository.deleteById(bookId);

    }
}
