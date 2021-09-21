package com.crud.library.repository;

import com.crud.library.domain.Reader;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderRepositoryTestSuite {
    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void testAddReader() {
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
