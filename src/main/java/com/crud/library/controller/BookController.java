package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/library/book/")
public class BookController {

    private final BookService service;
    private final BookMapper bookMapper;

    @PostMapping(value = "addBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookDto addBook(@RequestBody BookDto bookDto){
        Book book = bookMapper.mapToBook(bookDto);
        return bookMapper.mapToBookDto(service.addBook(book));

    }

    @GetMapping(value = "getBook")
    public BookDto getBook(@RequestParam Long bookId){
        return bookMapper.mapToBookDto(service.getBookById(bookId).orElseThrow());
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId){
        service.deleteBook(bookId);
    }

    @GetMapping(value = "getAllBooks")
    public List<BookDto> getAllBooks(){
        List<Book> books = service.getAllBooks();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : books){
            BookDto bookDto = bookMapper.mapToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }
}
