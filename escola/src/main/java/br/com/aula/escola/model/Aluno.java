package br.com.aula.escola.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome e obrigatorio")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O e-mail e obrigatorio")
    @Email(message = "Informe um e-mail valido")
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @NotNull(message = "Informe a nota")
    @DecimalMin(value = "0.0", message = "A nota minima e 0")
    @DecimalMax(value = "10.0", message = "A nota maxima e 10")
    @Column(nullable = false)
    private Double nota;

    public Aluno() {
    }

    public Aluno(String nome, String email, Double nota) {
        this.nome = nome;
        this.email = email;
        this.nota = nota;
    }

    // Calculado em memoria: nao vira coluna no banco
    @Transient
    public boolean isAprovado() {
        return nota != null && nota >= 7.0;
    }

    @Transient
    public String getSituacao() {
        return isAprovado() ? "Aprovado" : "Reprovado";
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
}