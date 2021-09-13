package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public void addReader(Reader reader){
        readerRepository.save(reader);
    }

    public Optional<Reader> findReader(Long id) {
        return readerRepository.findReaderById(id);
    }

    public void deleteReader(Long id) { readerRepository.deleteById(id);}

    public Reader saveReader(Reader reader){ return readerRepository.save(reader);}

}
