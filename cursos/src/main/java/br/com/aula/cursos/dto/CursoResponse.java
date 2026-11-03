package br.com.aula.cursos.dto;

import br.com.aula.cursos.model.Aluno;
import br.com.aula.cursos.model.Curso;
import java.util.List;

// O DTO corta o ciclo: devolve os nomes, nao as entidades ligadas de volta ao curso.
public record CursoResponse(Long id, String nome, Integer cargaHoraria, int totalAlunos,
                            List<String> alunos) {

    public static CursoResponse from(Curso curso) {
        return new CursoResponse(
                curso.getId(),
                curso.getNome(),
                curso.getCargaHoraria(),
                curso.getAlunos().size(),
                curso.getAlunos().stream().map(Aluno::getNome).toList());
    }
}
