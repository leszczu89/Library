package com.crud.library.repository;

import com.crud.library.domain.Borrowing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BorrowingRepositoryTestSuite {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Test
    void testAddNewBorrow() {
        //Given
        Borrowing borrowing = new Borrowing();
        //When
        borrowingRepository.save(borrowing);
        Long borrowId = borrowing.getId();
        //Then
        assertTrue(borrowingRepository.existsById(borrowId));
        //Clean up
        borrowingRepository.delete(borrowing);
    }
}
