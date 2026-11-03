package br.com.aula.biblioteca.config;

import br.com.aula.biblioteca.model.Livro;
import br.com.aula.biblioteca.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DadosIniciais {

    @Bean
    CommandLineRunner carregarLivros(LivroRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }
            repository.save(new Livro("Dom Casmurro", "Machado de Assis", 1899));
            repository.save(new Livro("Grande Sertao: Veredas", "Joao Guimaraes Rosa", 1956));
            repository.save(new Livro("A Hora da Estrela", "Clarice Lispector", 1977));
        };
    }
}
