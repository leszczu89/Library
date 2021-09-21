package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.status.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyRepositoryTestSuite {

    @Autowired
    BookCopyRepository bookCopyRepository;
    @Autowired
    BookRepository bookRepository;

    @Test
    void testAddBookCopy() {
        //Given

        Book book1 = new Book();
        bookRepository.save(book1);
        Long bookId = book1.getId();
        BookCopy bookCopy = new BookCopy(book1, Status.AVAILABLE);
        //When
        bookCopyRepository.save(bookCopy);
        Long bookCopyId = bookCopy.getId();
        //Then
        assertTrue(bookCopyRepository.existsById(bookCopyId));
        //Clean up

        bookCopyRepository.deleteById(bookCopyId);
        bookRepository.deleteById(bookId);
    }
}
