package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.dto.BookCopyDto;
import com.crud.library.domain.status.Status;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.service.BookCopyService;
import com.crud.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/library/copy")
@RequiredArgsConstructor
public class BookCopyController {
    private final BookCopyMapper bookCopyMapper;
    private final BookCopyService service;
    private final BookService bookService;

    @PostMapping(value = "addBookCopy")
    public BookCopyDto addBookCopy(@RequestParam Long bookId){
        Book book = bookService.getBook(bookId);
        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        service.addBookCopy(bookCopy);
        return bookCopyMapper.mapToBookCopyDto(bookCopy);
    }
    @PutMapping(value = "updateBookStatus")
    public BookCopyDto updateBookStatus(@RequestBody BookCopyDto bookCopyDto){
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        BookCopy bookWithChangedStatus = service.addBookCopy(bookCopy);
        return bookCopyMapper.mapToBookCopyDto(bookWithChangedStatus);
    }
    @GetMapping(value = "getCopiesNumber")
    public List<BookCopyDto> getAvailableCopies(@RequestParam Long bookId){
        List<BookCopy> availableCopies = service.returnAvailableCopies(bookId);
        return bookCopyMapper.mapToBookCopyDtoList(availableCopies);
    }
    @PutMapping(value = "updateCopyAsLost")
    public BookCopyDto updateCopyAsLost(@RequestParam Long bookCopyId){
        BookCopy lostCopy = service.changeBookCopyStatus(bookCopyId, Status.LOST);
        return bookCopyMapper.mapToBookCopyDto(lostCopy);
    }
    @GetMapping(value = "getBookCopy")
    public BookCopyDto getBookCopyById(@RequestParam Long bookCopyId){
        BookCopy bookCopy = service.getBookCopyById(bookCopyId);
        return bookCopyMapper.mapToBookCopyDto(bookCopy);
    }
    @DeleteMapping(value = "deleteBookCopy")
    public void deleteBookCopyById(@RequestParam Long bookCopyId){
        service.deleteBookCopyById(bookCopyId);
    }
}
