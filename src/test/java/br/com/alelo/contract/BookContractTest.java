package br.com.alelo.contract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alelo.controller.dto.BookDTO;
import br.com.alelo.utils.Converter;
import lombok.SneakyThrows;
import net.minidev.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith( SpringJUnit4ClassRunner.class )
public class BookContractTest {

    @Autowired
    private MockMvc mockMvc;
    
    private static final String PATH_BOOKS = "/books";
    
    @Test
    @SneakyThrows
    public void testAgeGroupNotEnv() {
        BookDTO b = BookDTO.builder()
        .author( "Kate" )
        .name( "Roque" )
        .build();
        
        String book = Converter.objectTo( b );
        
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_BOOKS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(book))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    @SneakyThrows
    public void testAgeGroupNull() {
        BookDTO b = BookDTO.builder()
                .author( "Kate" )
                .name( "Roque" )
                .ageGroupEnum( null )
                .build();
        
        String book = Converter.objectTo( b );
        
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_BOOKS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(book))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    @SneakyThrows
    public void testAgeGroupEmpity() {
        
        JSONObject book = new JSONObject();
        book.put( "author", "Kate" );
        book.put( "name", "Roque" );
        book.put( "ageGroupEnum", "" );
        
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_BOOKS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(book.toString()))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    @SneakyThrows
    public void testAgeGroupCharacters() {
        
        JSONObject book = new JSONObject();
        book.put( "author", "Kate" );
        book.put( "name", "Roque" );
        book.put( "ageGroupEnum", "@%$##" );
        
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_BOOKS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(book.toString()))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    @SneakyThrows
    public void testAgeGroupToUpperCase() {
        
        JSONObject book = new JSONObject();
        book.put( "author", "Kate" );
        book.put( "name", "Roque" );
        book.put( "ageGroupEnum", "ADULT" );
        
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_BOOKS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(book.toString()))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    @Test
    @SneakyThrows
    public void testAgeGroupToLowerCase() {
        
        JSONObject book = new JSONObject();
        book.put( "author", "Kate" );
        book.put( "name", "Roque" );
        book.put( "ageGroupEnum", "adult" );
        
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_BOOKS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(book.toString()))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    @Test
    @SneakyThrows
    public void testAgeGroupWithSpacing() {
        
        JSONObject book = new JSONObject();
        book.put( "author", "Kate" );
        book.put( "name", "Roque" );
        book.put( "ageGroupEnum", " " );
        
        mockMvc.perform(MockMvcRequestBuilders.post(PATH_BOOKS)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(book.toString()))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
}
