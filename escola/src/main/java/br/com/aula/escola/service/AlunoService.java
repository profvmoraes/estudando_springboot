package br.com.aula.escola.service;

import br.com.aula.escola.model.Aluno;
import br.com.aula.escola.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> listar() {
        return repository.findAll(Sort.by("nome"));
    }

    @Transactional
    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
