package br.com.alelo.controller.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alelo.enums.AgeGroupEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude( JsonInclude.Include.NON_NULL )
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 100, message = "{invalid.name}" )
    private String name;
    
    @JsonProperty("author")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 50, message = "{invalid.author}" )
    private String author;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "cannot be null")
    @ApiModelProperty(value = "Age Group", required = true, position = 3)
    private AgeGroupEnum ageGroupEnum;
    
}
