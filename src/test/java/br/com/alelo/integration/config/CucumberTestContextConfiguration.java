package br.com.alelo.integration.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import br.com.alelo.BookApplication;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=9090")
@ActiveProfiles(profiles={"it"}, resolver=ProfilesResolver.class)
public class CucumberTestContextConfiguration {

}
