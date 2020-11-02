Feature: Responsavel pelos livros
 
    Background:
        * url 'http://localhost:8080'
        * header Accept = 'application/json'
        
    @test_criar    
    Scenario Outline: Criar livros
    
        Given path 'books'
        And request { author: '<author>', name: '<name>', ageGroupEnum: '<ageGroupEnum>'}
        When method POST
        Then status 201
        And match $.id == <id>
        #And match $ contains { id: "#notnull" }
        
        Examples:
        	| 	author		|	name		|	ageGroupEnum	|	id	| 
        	|	Karate 1	|	kt karate 1	|	YOUNG			|	4	|
        	|	Karate 2	|	kt karate 2	|	YOUNG			|	5	|
        	|	Karate 3	|	kt karate 3	|	YOUNG			|	6	|

    @teste_listar
    Scenario: Listar livros
    
        Given path 'books'
        When method GET
        Then status 200
        And match $.length() == 6
        And match response contains { "name": "O Exorcista", "author": "William Petter", id: 1  }
        
	@teste_atualizar
	Scenario: Atualizar livros
    
        Given path 'books/1'
        And request { "author": "Karate update", "name": "kt karate update", "ageGroupEnum": "YOUNG" }
        When method PUT
        Then status 200
        #And match $ contains { id: 1 }
        And match response contains { "author": "Karate update", "name": "kt karate update", id: 1  }
