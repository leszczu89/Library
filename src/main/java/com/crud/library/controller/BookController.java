package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/library/book/")
public class BookController {

    private final BookService service;
    private final BookMapper bookMapper;

    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto){
        Book book = bookMapper.mapToBook(bookDto);
        service.addBook(book);
    }

    @GetMapping(value = "getBook")
    public BookDto getBook(@RequestParam Long bookId){
        return bookMapper.mapToBookDto(service.getBookById(bookId).orElseThrow());
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId){
        service.deleteBook(bookId);
    }
}
