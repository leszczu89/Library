package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookCopyRepositoryTestSuite {

    @Autowired
    BookCopyRepository bookCopyRepository;
    @Autowired
    BookRepository bookRepository;

    @Test
    void addBookCopyTest() {
        //Given
        Book book = new Book();
        bookRepository.save(book);
        BookCopy bookCopy = new BookCopy();
        //When
        bookCopyRepository.save(bookCopy);
        Long bookCopyId = bookCopy.getId();
        //Then
        assertTrue(bookCopyRepository.existsById(bookCopyId));
        //Clean up
        bookRepository.delete(book);
        bookCopyRepository.delete(bookCopy);
    }
}
