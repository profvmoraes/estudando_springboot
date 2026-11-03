package br.com.aula.biblioteca.dto;

import br.com.aula.biblioteca.model.Livro;

public record LivroResponse(Long id, String titulo, String autor, Integer anoPublicacao,
                            boolean disponivel) {

    public static LivroResponse from(Livro livro) {
        return new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAutor(),
                livro.getAnoPublicacao(), livro.isDisponivel());
    }
}
