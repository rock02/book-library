package br.com.alelo.repository.custom;

import br.com.alelo.domain.Book;

public interface BookRepositoryCustom {

    Book findByName(String name);
}
