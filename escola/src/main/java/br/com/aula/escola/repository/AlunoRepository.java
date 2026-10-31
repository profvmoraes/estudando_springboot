package br.com.aula.escola.repository;

import br.com.aula.escola.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
    List<Aluno> findByNotaGreaterThanEqual(Double nota);

    boolean existsByEmail(String email);
}
