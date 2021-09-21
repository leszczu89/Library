package com.crud.library.controller;

import com.crud.library.domain.dto.ReaderDto;
import com.crud.library.exception.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderControllerTestSuite {

    @Autowired
    private ReaderController readerController;

    @Test
    void testAddReader(){
        //Given
        ReaderDto readerDto = new ReaderDto("name", "lastname", LocalDate.now());
        //When
        ReaderDto adderReader = readerController.addReader(readerDto);
        Long readerId = adderReader.getId();
        //Then
        assertEquals("name", adderReader.getName());
        assertEquals("lastname", adderReader.getLastName());
        //Clean up
        readerController.deleteReader(readerId);
    }
    @Test
    void testDeleteReader(){
        //Given
        ReaderDto readerDto = new ReaderDto("n", "l", LocalDate.now());
        ReaderDto adderReader = readerController.addReader(readerDto);
        Long readerId = adderReader.getId();
        //When
        readerController.deleteReader(readerId);
        //Then
        assertThrows(NoSuchElementException.class, ()-> readerController.getReader(readerId));
    }
    @Test
    void testGetReader(){
        //Given
        ReaderDto readerDto = new ReaderDto("n", "l", LocalDate.now());
        ReaderDto adderReader = readerController.addReader(readerDto);
        Long readerId = adderReader.getId();
        //When
        ReaderDto returnedReader = null;
        try {
            returnedReader = readerController.getReader(readerId);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        //Then
        assertEquals("n", returnedReader.getName());
        assertEquals("l", returnedReader.getLastName());
        //Clean up
        readerController.deleteReader(readerId);
    }
}
