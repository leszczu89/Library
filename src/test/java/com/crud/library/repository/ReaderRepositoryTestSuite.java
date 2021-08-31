package com.crud.library.repository;

import com.crud.library.domain.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReaderRepositoryTestSuite {
    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void addReaderTest() {


        //Given
        Reader reader = new Reader();

        //When
        readerRepository.save(reader);

        //Then
        assertTrue(readerRepository.existsById(1L));
        //Clean Up
        readerRepository.delete(reader);

    }
}
