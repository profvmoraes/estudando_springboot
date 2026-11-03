package br.com.aula.cursos.service;

import br.com.aula.cursos.dto.AlunoRequest;
import br.com.aula.cursos.dto.CursoResponse;
import br.com.aula.cursos.exception.RecursoNaoEncontradoException;
import br.com.aula.cursos.model.Aluno;
import br.com.aula.cursos.model.Curso;
import br.com.aula.cursos.repository.CursoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<CursoResponse> listar() {
        return repository.findAll().stream().map(CursoResponse::from).toList();
    }

    public CursoResponse buscarPorId(Long id) {
        return CursoResponse.from(buscarEntidade(id));
    }

    @Transactional
    public CursoResponse cadastrar(String nome, Integer cargaHoraria) {
        Curso curso = new Curso(nome, cargaHoraria);
        return CursoResponse.from(repository.save(curso));
    }

    @Transactional
    public CursoResponse matricular(Long cursoId, AlunoRequest request) {
        Curso curso = buscarEntidade(cursoId);
        curso.adicionarAluno(new Aluno(request.nome(), request.email()));
        // cascade = ALL: salvar o curso tambem salva o aluno novo
        return CursoResponse.from(repository.save(curso));
    }

    @Transactional
    public void remover(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Curso buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Curso nao encontrado: " + id));
    }
}
