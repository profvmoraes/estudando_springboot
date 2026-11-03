package br.com.aula.escola.repository;

import br.com.aula.escola.model.Aluno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNomeContainingIgnoreCase(String nome);

    List<Aluno> findByNotaGreaterThanEqual(Double nota);

    boolean existsByEmail(String email);
}