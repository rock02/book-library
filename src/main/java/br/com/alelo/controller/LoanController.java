package br.com.alelo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.response.LoanResponse;
import br.com.alelo.service.LoanService;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/loans")
public class LoanController implements LoanDefinition {

    @Autowired
    private LoanService loanService; 
    
    @Override
    @PostMapping("/{bookId}/{studentId}")
    public ResponseEntity<LoanResponse> create( @PathVariable @ApiParam( value = "bookId", required = true ) Long bookId, @PathVariable @ApiParam( value = "studentId", required = true ) Long studentId ) {
        return new ResponseEntity<>(loanService.create( bookId, studentId ), HttpStatus.CREATED );
    }
    
}
