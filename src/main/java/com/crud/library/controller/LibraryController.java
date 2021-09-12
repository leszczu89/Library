package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Borrowing;
import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.BorrowingDto;
import com.crud.library.domain.dto.ReaderDto;
import com.crud.library.domain.status.Status;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.BorrowingsMapper;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v2/library")
@RequiredArgsConstructor
public class LibraryController {
    private final BookCopyMapper bookCopyMapper;
    private final BookMapper bookMapper;
    private final ReaderMapper readerMapper;
    private final BorrowingsMapper borrowingsMapper;
    private final DbService dbService;

    @PostMapping(value = "addReader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto){
        Reader reader = readerMapper.mapToReader(readerDto);
        dbService.addReader(reader);
    }

    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto){
        Book book = bookMapper.mapToBook(bookDto);
        dbService.addBook(book);
    }

    @PostMapping(value = "addBookCopy")
    public BookCopyDto addBookCopy(@RequestParam Long bookId){
        Book book = dbService.getBook(bookId);
        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        dbService.addBookCopy(bookCopy);
        return bookCopyMapper.mapToBookCopyDto(bookCopy);
    }

    @PutMapping(value = "updateBookStatus")
    public BookCopyDto updateBookStatus(@RequestBody BookCopyDto bookCopyDto){
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        BookCopy bookWithChangedStatus = dbService.addBookCopy(bookCopy);
        return bookCopyMapper.mapToBookCopyDto(bookWithChangedStatus);
    }

    @GetMapping(value = "getCopiesNumber")
    public List<BookCopyDto> getAvailableCopies(@RequestBody BookDto bookDto){
        Book book = bookMapper.mapToBook(bookDto);
        List<BookCopy> availableCopies = dbService.returnAvailableCopies(book);
        return bookCopyMapper.mapToBookCopyDtoList(availableCopies);
    }
    @Transactional
    @PostMapping(value = "addBorrowing")
    public BorrowingDto addBorrowing(@RequestParam Long bookCopyId, @RequestParam Long readerId){
        BookCopy bookCopy = dbService.changeBookCopyStatus(bookCopyId, Status.BORROWED);
        Reader reader = dbService.findReader(readerId);
        Borrowing newBorrowing = new Borrowing(bookCopy, reader, LocalDate.now());
        Borrowing savedBorrowing = dbService.borrowBook(newBorrowing);
        return borrowingsMapper.mapToBorrowingDto(savedBorrowing);
    }

    @PutMapping(value = "bookReturn")
    public BorrowingDto bookReturn(@RequestParam Long bookCopyId){
        dbService.changeBookCopyStatus(bookCopyId, Status.AVAILABLE);
        Borrowing updatedBorrowing = dbService.returnBook(bookCopyId);
        return borrowingsMapper.mapToBorrowingDto(updatedBorrowing);
    }

    @PutMapping(value = "updateCopyAsLost")
    public BookCopyDto updateCopyAsLost(@RequestParam Long bookCopyId){
        BookCopy lostCopy = dbService.changeBookCopyStatus(bookCopyId, Status.LOST);
        return bookCopyMapper.mapToBookCopyDto(lostCopy);
    }
}
