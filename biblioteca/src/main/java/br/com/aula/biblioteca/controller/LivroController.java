package br.com.aula.biblioteca.controller;

import br.com.aula.biblioteca.dto.LivroRequest;
import br.com.aula.biblioteca.dto.LivroResponse;
import br.com.aula.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping
    public List<LivroResponse> listar(@RequestParam(required = false) String autor) {
        return autor == null ? service.listar() : service.buscarPorAutor(autor);
    }

    @GetMapping("/{id}")
    public LivroResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<LivroResponse> cadastrar(@Valid @RequestBody LivroRequest request) {
        LivroResponse criado = service.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }


    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequest request) {
        return service.atualizar(id, request);
    }

    @PatchMapping("/{id}/emprestimo")
    public LivroResponse emprestar(@PathVariable Long id) {
        return service.emprestar(id);
    }

    @PatchMapping("/{id}/devolucao")
    public LivroResponse devolver(@PathVariable Long id) {
        return service.devolver(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
