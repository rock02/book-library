package br.com.alelo.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.adapter.BookAdapter;
import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.domain.Book;
import br.com.alelo.enums.ExceptionsMessagesEnum;
import br.com.alelo.exception.BadRequest;
import br.com.alelo.repository.custom.BookRepositoryImpl;
import br.com.alelo.repository.jpa.BookRepository;
import br.com.alelo.response.BookResponse;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookRepositoryImpl bookRepositoryImpl;
    
    @Autowired
    private BookAdapter bookAdapter;
    
    public BookResponse save(BookDTO bookDTO) {
        
        BadRequest.checkThrow( Objects.nonNull( bookRepositoryImpl.findByName( bookDTO.getName() ) ),
                ExceptionsMessagesEnum.BOOK_ALREADY_REGISTERED );
        
        Book book = bookRepository.save( bookAdapter.bookDtoToEntity( bookDTO ) );
        
        return BookResponse.builder()
                .id( book.getId() )
                .build();
    }
    
    public BookResponse update(Long id, BookDTO bookDTOUpdate) {
        
        BadRequest.checkThrow( Objects.nonNull( bookRepository.findByName( bookDTOUpdate.getName().toUpperCase() ) ),
                ExceptionsMessagesEnum.BOOK__NAME_ALREADY_REGISTERED );
        
        BadRequest.checkThrow( Objects.isNull( bookRepository.findById( id ) ),
                ExceptionsMessagesEnum.BOOK_NOT_FOUND );
        
        Optional<Book> oldBook = bookRepository.findById( id );
        
        Book bookUpdated = bookRepository.saveAndFlush( mountUpdate( oldBook.get(), bookDTOUpdate ) );
        
        return bookAdapter.bookEntityToDto( bookUpdated );
    }
    
    private Book mountUpdate(Book book, BookDTO bookDTOUpdate) {
        
        return Book.builder()
        .id(book.getId())
        .name( bookDTOUpdate.getName() )
        .author( bookDTOUpdate.getAuthor() )
        .build();
    }
}
