package br.com.alelo.integration.loan;

import static io.restassured.RestAssured.given;

import org.springframework.stereotype.Component;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Component
public class LoanServiceInformation {

    @Getter
    private Response response;
    
    public void create( Long studentId, Long bookId ) {

        RequestSpecBuilder request = new RequestSpecBuilder();

        RequestSpecification requestSpec = request.build();

        Response response = given().log().all().spec( requestSpec ).post( String.format( "/loans/%s/%s", bookId, studentId ) );

        response.then().log().all();

        this.response = response;
    }
        
}
