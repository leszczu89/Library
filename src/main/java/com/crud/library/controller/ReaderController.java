package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.ReaderDto;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v2/library/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService service;
    private final ReaderMapper readerMapper;

    @PostMapping(value = "addReader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto){
        Reader reader = readerMapper.mapToReader(readerDto);
        service.addReader(reader);
    }

    @DeleteMapping(value = "deleteReader")
    public void deleteReader(@RequestParam Long readerId){
        service.deleteReader(readerId);
    }

    @GetMapping(value = "getReader")
    public ReaderDto getReader(@RequestParam Long readerId){
        return readerMapper.mapToReaderDto(service.findReader(readerId).orElseThrow());
    }

    @PutMapping(value = "updateReader")
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto){
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader savedReader = service.saveReader(reader);
        return readerMapper.mapToReaderDto(savedReader);
    }


}
