package com.crud.library.controller;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Borrowing;
import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.BorrowingDto;
import com.crud.library.domain.status.Status;
import com.crud.library.mapper.BorrowingsMapper;
import com.crud.library.service.BookCopyService;
import com.crud.library.service.BorrowingService;
import com.crud.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/library/borrowing")
public class BorrowingController {

    private final BorrowingsMapper borrowingsMapper;
    private final BorrowingService service;
    private final BookCopyService bookCopyService;
    private final ReaderService readerService;

    @Transactional
    @PostMapping(value = "addBorrowing")
    public BorrowingDto addBorrowing(@RequestParam Long bookCopyId, @RequestParam Long readerId){
        BookCopy bookCopy = bookCopyService.changeBookCopyStatus(bookCopyId, Status.BORROWED);
        Reader reader = readerService.findReader(readerId).orElseThrow();
        Borrowing newBorrowing = new Borrowing(bookCopy, reader, LocalDate.now());
        Borrowing savedBorrowing = service.borrowBook(newBorrowing);
        return borrowingsMapper.mapToBorrowingDto(savedBorrowing);
    }

    @PutMapping(value = "bookReturn")
    public BorrowingDto bookReturn(@RequestParam Long bookCopyId){
        bookCopyService.changeBookCopyStatus(bookCopyId, Status.AVAILABLE);
        Borrowing updatedBorrowing = service.returnBook(bookCopyId);
        return borrowingsMapper.mapToBorrowingDto(updatedBorrowing);
    }

    @DeleteMapping(value = "deleteBorrowing")
    public void deleteBorrowing(@RequestParam Long borrowingId){
        service.deleteBorrowingById(borrowingId);
    }
}
