package br.com.alelo.integration.student;

import static io.restassured.RestAssured.given;

import org.springframework.stereotype.Component;

import br.com.alelo.controller.dto.StudentDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

@Component
public class StudentServiceInformation {

    @Getter
    @Setter
    private Long studentId;
    
    public Response create(StudentDTO studentDTO) {
        
        RequestSpecBuilder request = new RequestSpecBuilder();
        
        request.setContentType( "application/json" );
        request.setBody( studentDTO );
        
        RequestSpecification requestSpec = request.build();
        
        Response response = given()
                    .log()
                        .all()
                        .spec( requestSpec )
                    .post( "/students" );
        
        response
            .then()
            .log()
                .all();
        
        return response;
    }
}
