package br.com.alelo.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.domain.Student;
import br.com.alelo.response.StudentResponse;

@Component
public class StudentAdapter {

    public Student studentDtoToEntity(StudentDTO studentDTO) {
        
        return Student.builder()
                .name( studentDTO.getName().toUpperCase() )
                .email( studentDTO.getEmail() )
                .years( studentDTO.getYears() )
                .cpf( studentDTO.getCpf() )
                .build();
    }
    
    public StudentResponse studentEntityToDto(Student student) {
        
        return StudentResponse.builder()
                .id( student.getId() )
                .name( student.getName() )
                .email( student.getEmail() )
                .years( String.valueOf( student.getYears() ) )
                .cpf( student.getCpf() )
                .build();
    }

    public List<StudentResponse> studentEntityToDtoForList( List<Student> students ) {

        List<StudentResponse> listStudentsResponse = new ArrayList<>();

        if (students == null) {
            return listStudentsResponse;
        }

        students.forEach( student -> {

            StudentResponse studentResponse = StudentResponse.builder()
                    .id( student.getId() )
                    .name( student.getName() )
                    .email( student.getEmail() )
                    .years( String.valueOf( student.getYears() ) )
                    .cpf( student.getCpf() )
                    .build();

            listStudentsResponse.add( studentResponse );
        } );

        return listStudentsResponse;
    }
}
