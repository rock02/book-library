package br.com.alelo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.alelo.response.LoanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Loan")
public interface LoanDefinition {

    @ApiOperation(value = "create", nickname = "create", notes = "crated loan")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<LoanResponse> create( @PathVariable @ApiParam( value = "bookId", required = true ) Long bookId, @PathVariable @ApiParam( value = "studentId", required = true ) Long studentId );
}
