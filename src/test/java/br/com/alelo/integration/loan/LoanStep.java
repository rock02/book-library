package br.com.alelo.integration.loan;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.github.javafaker.Faker;

import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.enums.AgeGroupEnum;
import br.com.alelo.integration.book.BookServiceInformation;
import br.com.alelo.integration.config.CucumberTestContextConfiguration;
import br.com.alelo.integration.student.StudentServiceInformation;
import br.com.alelo.integration.utils.HttpStatusUtils;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoanStep extends CucumberTestContextConfiguration {

    @Autowired
    private LoanServiceInformation loanServiceInformation;
    
    @Autowired
    private BookServiceInformation bookServiceInformation;
    
    @Autowired
    private StudentServiceInformation studentServiceInformation;
    
    private Faker faker = new Faker();
    
    private static final String POST = "POST";
    
    @Before
    public void before() {

        RestAssured.baseURI = "http://localhost:9090";
        
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
    }
    
    @ParameterType(".*")
    public AgeGroupEnum ageGroup(String ageGroup) {
        return AgeGroupEnum.fromValue( ageGroup );
    }
    
    @Given("Que eu possua um livro {ageGroup}")
    public void que_eu_possua_um_livro(AgeGroupEnum ageGroupEnum) {
        BookDTO bookDTO = BookDTO.builder()
                .name( faker.book().title() )
                .author( faker.book().author() )
                .ageGroupEnum( ageGroupEnum )
                .build();
                
        Response response = bookServiceInformation.create( bookDTO );
        
        Long bookId = Long.parseLong( response.jsonPath().get( "id" ).toString() );
        
        this.bookServiceInformation.setBookId( bookId );
    }
    
    @And("Que eu possua um estudante {int}")
    public void que_eu_possua_um_estudante(int years ) {
        
        StudentDTO studentDTO  = StudentDTO.builder()
                .name( faker.name().firstName() )
                .cpf( String.format( "%s%s", faker.number().numberBetween( 11111, 99999 ), faker.number().numberBetween( 111111, 999999 ) ) )
                .email( "automation@gmail.com" )
                .years( years )
                .build();
        
        Response response = studentServiceInformation.create( studentDTO );
        
        Long studentID = Long.parseLong( response.jsonPath().get( "id" ).toString() );
        
        this.studentServiceInformation.setStudentId( studentID );
    }
    
    @When("Enviar requisicao {string} para api de emprestimos")
    public void enviar_requisicao_para_api_emprestimos(String type) {
        if (POST.equalsIgnoreCase( type )) {
            
            this.loanServiceInformation.create( this.studentServiceInformation.getStudentId(), this.bookServiceInformation.getBookId() );
        } else {
            Assert.fail( String.format( "type does not exist: %s", type ) );
        }
    }
    
    @Given("Que eu possua um estudante com emprestimo")
    public void que_eu_possua_estudante_como_emprestimo() {
        this.que_eu_possua_um_livro( AgeGroupEnum.ADULT );
        this.que_eu_possua_um_estudante( 20 );
        this.enviar_requisicao_para_api_emprestimos( POST );
    }
    
    @Then("Validar {string} retorno")
    public void validar_retorno(String statusName) {
        
        HttpStatus returnedCode = HttpStatusUtils.returnedCodeStatus( statusName );
        
        this.loanServiceInformation
        .getResponse()
        .then()
        .statusCode( returnedCode.value() )
        .log()
        .all();
    }
    
}
