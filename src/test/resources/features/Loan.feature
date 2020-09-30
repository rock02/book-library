@tests_loan
Feature: Responsavel pelos emprestimos

#----------------------------------------------------------------CRIAR----------------------------------------------------------------##
    
	@realizar_criacao_de_emprestimos_para_estudante
	Scenario Outline: Realizar criacao de emprestimos para um estudante
	Given Que eu possua um livro <BOOK>
		And Que eu possua um estudante <STUDENT> 
		When Enviar requisicao "POST" para api de emprestimos
		Then Validar "<RETURN>" retorno
		
		Examples:
			| 	BOOK	|	STUDENT		|	RETURN		|
			|	ADULT	|	20			|	CREATED		|
			|	YOUNG	|	19			|	CREATED		|
			|	OLD		|	60			|	CREATED		|
		
	@realizar_criacao_de_emprestimos_duplicado_para_um_estudante
	Scenario: Realizar criacao de emprestimos duplicado para um estudante
	Given Que eu possua um estudante com emprestimo
		When Enviar requisicao "POST" para api de emprestimos
		Then Validar "BAD_REQUEST" retorno