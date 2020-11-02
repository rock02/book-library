package br.com.alelo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.adapter.BookAdapter;
import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.repository.jpa.BookRepository;
import br.com.alelo.response.BookResponse;
import br.com.alelo.service.BookService;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/books")
public class BookController implements BookDefinition {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookAdapter bookAdapter;
    
    @Autowired
    private BookRepository bookRepository;
    
    @GetMapping
    @Override
    public ResponseEntity<List<BookResponse>> getAll() {
        
        return new ResponseEntity<>( bookAdapter.booksEntityToDtoForList( bookRepository.findAll() ), HttpStatus.OK );
    }

    @PostMapping
    @Override
    public ResponseEntity<BookResponse> create( @Valid @RequestBody BookDTO bookDTO ) {
        return new ResponseEntity<>( bookService.save( bookDTO ), HttpStatus.CREATED );
    }

    @PutMapping("/{bookId}")
    @Override
    public ResponseEntity<BookResponse> update( @PathVariable @ApiParam( value = "bookId", required = true ) Long bookId, @Valid @RequestBody BookDTO bookDTOUpdate ) {
        return new ResponseEntity<>( bookService.update( bookId, bookDTOUpdate ), HttpStatus.OK );
    }

}
