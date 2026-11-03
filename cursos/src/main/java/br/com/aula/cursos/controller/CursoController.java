package br.com.aula.cursos.controller;

import br.com.aula.cursos.dto.AlunoRequest;
import br.com.aula.cursos.dto.CursoRequest;
import br.com.aula.cursos.dto.CursoResponse;
import br.com.aula.cursos.service.CursoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CursoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CursoResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<CursoResponse> cadastrar(@Valid @RequestBody CursoRequest request) {
        CursoResponse criado = service.cadastrar(request.nome(), request.cargaHoraria());
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PostMapping("/{id}/alunos")
    public CursoResponse matricular(@PathVariable Long id, @Valid @RequestBody AlunoRequest request) {
        return service.matricular(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
