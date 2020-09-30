package br.com.alelo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.adapter.LoanAdapter;
import br.com.alelo.domain.Book;
import br.com.alelo.domain.Loan;
import br.com.alelo.domain.Student;
import br.com.alelo.enums.AgeGroupEnum;
import br.com.alelo.enums.ExceptionsMessagesEnum;
import br.com.alelo.exception.BadRequest;
import br.com.alelo.repository.jpa.BookRepository;
import br.com.alelo.repository.jpa.LoanRepository;
import br.com.alelo.repository.jpa.StudentRepository;
import br.com.alelo.response.LoanResponse;

@Service
public class LoanService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private LoanAdapter loanAdapter;
    
    public LoanResponse create( Long bookId, Long studentId ) {
        
        Optional<Student> student = studentRepository.findById( studentId );
        
        BadRequest.checkThrow( !student.isPresent(),
                ExceptionsMessagesEnum.STUDENT_NOT_FOUND );
        
        Optional<Book> book = bookRepository.findById( bookId );
        
        BadRequest.checkThrow( !book.isPresent(),
                ExceptionsMessagesEnum.BOOK_NOT_FOUND );
        
        Optional<Loan> loan = loanRepository.findByBookIdAndStudentId( book.get().getId(), student.get().getId() );
        
        BadRequest.checkThrow( loan.isPresent(),
                ExceptionsMessagesEnum.LOAN_ALREADY_REGISTERED );
        
        if (isAdult( student.get().getYears() ) 
                && book.get().getAgeGoup().equalsIgnoreCase( AgeGroupEnum.ADULT.name() ) ) {
            
            Loan loanSave = loanRepository.save( Loan.builder()
                    .bookId( bookId )
                    .studentId( studentId )
                    .loanDate( LocalDateTime.now() )
                    .build() );
            
            return loanAdapter.loanEntityToDto( loanSave );
        }
        
        if (isYoung( student.get().getYears() ) 
                && book.get().getAgeGoup().equalsIgnoreCase( AgeGroupEnum.YOUNG.name() ) ) {
            
            Loan loanSave = loanRepository.save( Loan.builder()
                    .bookId( bookId )
                    .studentId( studentId )
                    .loanDate( LocalDateTime.now() )
                    .build() );
            return loanAdapter.loanEntityToDto( loanSave );
        }
        
        if (isOld( student.get().getYears() ) 
                && book.get().getAgeGoup().equalsIgnoreCase( AgeGroupEnum.OLD.name() ) ) {
            
            Loan loanSave = loanRepository.save( Loan.builder()
                    .bookId( bookId )
                    .studentId( studentId )
                    .loanDate( LocalDateTime.now() )
                    .build() );
            
            return loanAdapter.loanEntityToDto( loanSave );
        }
        
        return null;
    }
    
    private Boolean isAdult( Integer years ) {

        if (years >= 20 && years <= 59) {
            return true;
        }

        return false;
    }

    private Boolean isYoung( Integer years ) {

        if (years <= 19) {
            return true;
        }

        return false;
    }

    private Boolean isOld( Integer years ) {

        if (years >= 60 && years <= 120) {
            return true;
        }

        return false;
    }
}
