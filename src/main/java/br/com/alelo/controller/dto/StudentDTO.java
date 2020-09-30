package br.com.alelo.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude( JsonInclude.Include.NON_NULL )
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @JsonProperty("name")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 20, message = "{invalid.name}" )
    private String name;
    
    @JsonProperty("email")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 20, message = "{invalid.email}" )
    private String email;
    
    @Range(min=1, max=100)
    private int years;
    
    @JsonProperty("cpf")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 11, message = "{invalid.cpf}" )
    private String cpf;
    
}
