package br.com.alelo.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByBookIdAndStudentId(Long book_id, Long student_id);
}
