package br.com.alelo.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponse implements Serializable {

    private static final long serialVersionUID = 3214847945723068490L;

    private Long id;
    
    private LocalDateTime loanDate;
    
}
