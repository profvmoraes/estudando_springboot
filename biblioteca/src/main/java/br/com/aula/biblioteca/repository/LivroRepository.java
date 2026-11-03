package br.com.aula.biblioteca.repository;

import br.com.aula.biblioteca.model.Livro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutorContainingIgnoreCase(String autor);

    List<Livro> findByDisponivelTrue();

    long countByDisponivelFalse();
}
