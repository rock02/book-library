package br.com.alelo.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

    Book findByName( String name);
}
