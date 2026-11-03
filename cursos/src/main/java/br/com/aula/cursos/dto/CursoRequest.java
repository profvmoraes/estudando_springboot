package br.com.aula.cursos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CursoRequest(

        @NotBlank(message = "O nome do curso e obrigatorio")
        String nome,

        @Min(value = 1, message = "A carga horaria deve ser maior que zero")
        Integer cargaHoraria) {
}
