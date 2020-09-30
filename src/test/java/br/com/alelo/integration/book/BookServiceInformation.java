package br.com.alelo.integration.book;

import static io.restassured.RestAssured.given;

import org.springframework.stereotype.Component;

import br.com.alelo.controller.dto.BookDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

@Component
public class BookServiceInformation {

    @Getter
    @Setter
    private Long bookId;
    
    public Response create(BookDTO bookDTO) {
        
        RequestSpecBuilder request = new RequestSpecBuilder();
        
        request.setContentType( "application/json" );
        request.setBody( bookDTO );
        
        RequestSpecification requestSpec = request.build();
        
        Response response = given()
                    .log()
                        .all()
                        .spec( requestSpec )
                    .post( "/books" );
        
        response
            .then()
            .log()
                .all();
        
        return response;
    }
}
