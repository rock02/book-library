package br.com.alelo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.response.BookResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags={ "Book" })
public interface BookDefinition {

    @ApiOperation(value = "getAll", nickname = "getAll", notes = "getAll books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<List<BookResponse>> getAll();

    @ApiOperation(value = "create", nickname = "create", notes = "created book")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<BookResponse> create( @Valid @RequestBody BookDTO bookDTO );
    
    @ApiOperation(value = "update", nickname = "update", notes = "update book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not fount"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<BookResponse> update( @PathVariable @Valid Long id, @Valid @RequestBody @ApiParam( name = "book", required = true ) BookDTO bookDTO );
}
