package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Service;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getName(),
                readerDto.getLastName(),
                readerDto.getCreationDate()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getLastname(),
                reader.getCreationDate()
        );
    }
}
