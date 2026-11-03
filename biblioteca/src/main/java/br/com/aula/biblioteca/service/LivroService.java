package br.com.aula.biblioteca.service;

import br.com.aula.biblioteca.dto.LivroRequest;
import br.com.aula.biblioteca.dto.LivroResponse;
import br.com.aula.biblioteca.exception.RecursoNaoEncontradoException;
import br.com.aula.biblioteca.exception.RegraDeNegocioException;
import br.com.aula.biblioteca.model.Livro;
import br.com.aula.biblioteca.repository.LivroRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<LivroResponse> listar() {
        return repository.findAll().stream().map(LivroResponse::from).toList();
    }

    public List<LivroResponse> buscarPorAutor(String autor) {
        return repository.findByAutorContainingIgnoreCase(autor).stream()
                .map(LivroResponse::from)
                .toList();
    }

    public LivroResponse buscarPorId(Long id) {
        return LivroResponse.from(buscarEntidade(id));
    }

    @Transactional
    public LivroResponse cadastrar(LivroRequest request) {
        Livro livro = new Livro(request.titulo(), request.autor(), request.anoPublicacao());
        return LivroResponse.from(repository.save(livro));
    }

    @Transactional
    public LivroResponse atualizar(Long id, LivroRequest request) {
        Livro livro = buscarEntidade(id);
        livro.setTitulo(request.titulo());
        livro.setAutor(request.autor());
        livro.setAnoPublicacao(request.anoPublicacao());
        return LivroResponse.from(repository.save(livro));
    }

    @Transactional
    public LivroResponse emprestar(Long id) {
        Livro livro = buscarEntidade(id);
        if (!livro.isDisponivel()) {
            throw new RegraDeNegocioException("O livro ja esta emprestado.");
        }
        livro.setDisponivel(false);
        return LivroResponse.from(repository.save(livro));
    }


    @Transactional
    public LivroResponse devolver(Long id) {
        Livro livro = buscarEntidade(id);
        if (livro.isDisponivel()) {
            throw new RegraDeNegocioException("O livro ja esta disponivel.");
        }
        livro.setDisponivel(true);
        return LivroResponse.from(repository.save(livro));
    }

    @Transactional
    public void remover(Long id) {
        Livro livro = buscarEntidade(id);
        repository.delete(livro);
    }

    private Livro buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro nao encontrado: " + id));
    }
}
