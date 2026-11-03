package br.com.aula.biblioteca.dto;

import jakarta.validation.constraints.*;

public record LivroRequest(

        @NotBlank(message = "O titulo e obrigatorio")
        @Size(max = 120, message = "O titulo deve ter no maximo 120 caracteres")
        String titulo,

        @NotBlank(message = "O autor e obrigatorio")
        String autor,

        @Min(value = 1400, message = "Ano de publicacao invalido")
        @Max(value = 2100, message = "Ano de publicacao invalido")
        Integer anoPublicacao
) {
}
