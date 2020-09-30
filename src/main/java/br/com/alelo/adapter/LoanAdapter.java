package br.com.alelo.adapter;

import org.springframework.stereotype.Component;

import br.com.alelo.domain.Loan;
import br.com.alelo.response.LoanResponse;

@Component
public class LoanAdapter {

    public LoanResponse loanEntityToDto( Loan loan ) {

        return LoanResponse.builder()
                .id( loan.getId() )
                .loanDate( loan.getLoanDate() )
                .build(); 
                
    }
}
