package com.crud.library.service;

import com.crud.library.domain.Reader;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderServiceTestSuite {
    @Autowired
    private ReaderService readerService;

    @Test
    public void testAddReader() {
        //Given
        Reader reader = new Reader("name", "lastname");
        //When
        readerService.addReader(reader);
        Long readerId = reader.getId();
        //Then
        assertEquals("name", readerService.findReader(readerId).get().getName());
        assertEquals("lastname", readerService.findReader(readerId).get().getLastname());
        //Clean Up
        readerService.deleteReader(readerId);
    }
    @Test
    public void testFindReaderById() {
        //Given
        Reader reader = new Reader("testName", "testLastname");
        readerService.addReader(reader);
        Long readerId = reader.getId();
        //When
        Optional<Reader> testedReader = readerService.findReader(readerId);
        //Then
        assertEquals("testName", testedReader.get().getName());
        assertEquals("testLastname", testedReader.get().getLastname());
        //Clean Up
        readerService.deleteReader(readerId);
    }
    @Test
    public void testDeleteReader() {
        //Given
        Reader reader = new Reader("n", "ln");
        readerService.addReader(reader);
        Long readerId = reader.getId();
        //When
        readerService.deleteReader(readerId);
        //Then
        assertEquals(Optional.empty(), readerService.findReader(readerId));
    }
    @Test
    public void testSaveReader() {
        //Given
        Reader reader = new Reader("Jan", "Kowalski");
        //When
        Reader testedReader = readerService.saveReader(reader);
        //Then
        assertEquals("Jan", testedReader.getName());
        assertEquals("Kowalski", testedReader.getLastname());
        assertNotNull(readerService.findReader(testedReader.getId()));
        //Clean Up
        readerService.deleteReader(testedReader.getId());
    }
}
