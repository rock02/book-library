package br.com.alelo.unit;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.alelo.enums.ExceptionsMessagesEnum;
import br.com.alelo.exception.BadRequest;
import br.com.alelo.exception.NotFound;
import br.com.alelo.response.LoanResponse;
import br.com.alelo.service.LoanService;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
public class LoanTest {

    @Autowired
    private LoanService loanService;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void testStudentAdultAgeGroupAndBook() {
        
        Long bookId = 1L;
        Long studentId = 2L;
        
        LoanResponse response = loanService.create( bookId, studentId );
        
        Assert.assertTrue( "I hope the result is not null: adult age group", Objects.nonNull( response ) );
    }
    
    @Test
    public void testStudentYoungAgeGroupAndBook() {
        
        Long bookId = 2L;
        Long studentId = 4L;
        
        LoanResponse response = loanService.create( bookId, studentId );
        
        Assert.assertTrue( "I hope the result is not null: young age group", Objects.nonNull( response ) );
    }
    
    @Test
    public void testStudentOldAgeGroupAndBook() {
        
        Long bookId = 3L;
        Long studentId = 3L;
        
        LoanResponse response = loanService.create( bookId, studentId );
        
        Assert.assertTrue( "I hope the result is not null: old age group", Objects.nonNull( response ) );
    }
    
    @Test
    public void testStudentNotFound() {
        
        expectedException.expect(NotFound.class);
        expectedException.expectMessage(ExceptionsMessagesEnum.STUDENT_NOT_FOUND.getMessage());
        
        Long bookId = 1L;
        Long studentId = 0L;
        
        loanService.create( bookId, studentId );
        
    }
    
    @Test
    public void testBookNotFound() {
        
        expectedException.expect(NotFound.class);
        expectedException.expectMessage(ExceptionsMessagesEnum.BOOK_NOT_FOUND.getMessage());
        
        Long bookId = 0L;
        Long studentId = 2L;
        
        loanService.create( bookId, studentId );
        
    }
    
    @Test
    public void testLoanDuplicatedByStudentAndBook() {
        
        expectedException.expect(BadRequest.class);
        expectedException.expectMessage(ExceptionsMessagesEnum.LOAN_ALREADY_REGISTERED.getMessage());
        
        Long bookId = 1L;
        Long studentId = 1L;
        
        loanService.create( bookId, studentId );
        
    }
    
    @Test
    public void testStudentNotAgeGroupAndBook() {
        
        Long bookId = 1L;
        Long studentId = 5L;
        
        LoanResponse response = loanService.create( bookId, studentId );
        
        Assert.assertTrue( "I hope the result is null: not age group", Objects.isNull( response ) );
    }
}
