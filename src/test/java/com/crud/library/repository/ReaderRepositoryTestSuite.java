package com.crud.library.repository;

import com.crud.library.domain.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReaderRepositoryTestSuite {
    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void addReaderTest() {
        //Given
        Reader reader = new Reader("Jan", "Kowalski");

        //When
        readerRepository.save(reader);
        Long id = reader.getId();
        //Then
        assertTrue(readerRepository.existsById(id));
        //Clean Up
        readerRepository.delete(reader);

    }
}
