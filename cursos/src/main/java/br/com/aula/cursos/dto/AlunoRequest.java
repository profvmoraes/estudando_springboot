package br.com.aula.cursos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlunoRequest(

        @NotBlank(message = "O nome e obrigatorio")
        @Size(max = 100)
        String nome,

        @NotBlank(message = "O e-mail e obrigatorio")
        @Email(message = "Informe um e-mail valido")
        String email
) {
}
