package br.com.alelo.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.domain.Book;
import br.com.alelo.response.BookResponse;

@Component
public class BookAdapter {

    public Book bookDtoToEntity( BookDTO bookDTO ) {

        return Book.builder()
                .name( bookDTO.getName() )
                .author( bookDTO.getAuthor() )
                .ageGoup( bookDTO.getAgeGroupEnum().name() )
                .build();
    }

    public BookResponse bookEntityToDto( Book book ) {

        return BookResponse.builder()
                .id( book.getId() )
                .name( book.getName() )
                .author( book.getAuthor() )
                .ageGroup( book.getAgeGoup() )
                .build();
    }
    
    public static List<BookResponse> booksEntityToDtoForList( List<Book> books ) {

        List<BookResponse> listBooksResponse = new ArrayList<>();

        if (books == null) {
            return listBooksResponse;
        }

        books.forEach( book -> {
            BookResponse bookDto = BookResponse.builder()
                    .id( book.getId() )
                    .name( book.getName() )
                    .author( book.getAuthor() )
                    .build();

            listBooksResponse.add( bookDto );
        } );

        return listBooksResponse;
    }
    
    public static List<Book> bookDtoToEntityForList( List<BookDTO> booksDTO ) {
        
        List<Book> listBooksEntity = new ArrayList<>();
        
        if (booksDTO == null) {
            return listBooksEntity;
        }
        
        booksDTO.forEach( book -> {
            Book bookDto = Book.builder()
                    .name( book.getName() )
                    .author( book.getAuthor() )
                    .build();
            
            listBooksEntity.add( bookDto );
        } );
        
        return listBooksEntity;
    }
}
