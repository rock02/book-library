package br.com.alelo.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.alelo.domain.Book;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Book findByName( String name ) {
        
        Query query = null;

        StringBuilder sql = new StringBuilder();
        
        sql.append( "SELECT * " )
        .append( "FROM BOOK " )
        .append( "WHERE UPPER(NAME) = :name" );
        
        query = entityManager.createNativeQuery(sql.toString(), Book.class);
        
        query.setParameter( "name", name.toUpperCase() );
        
        try {
            return (Book) query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

}
