package br.com.aula.escola.controller;

import br.com.aula.escola.model.Aluno;
import br.com.aula.escola.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", service.listar());
        return "alunos/lista";
    }

    @GetMapping("/novo")
    public String formulario(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "alunos/formulario";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("aluno") Aluno aluno,
                         BindingResult resultado,
                         RedirectAttributes redirect) {

        if (resultado.hasErrors()) {
            return "alunos/formulario";
        }
        service.salvar(aluno);
        redirect.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
        return "redirect:/alunos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        service.excluir(id);
        redirect.addFlashAttribute("mensagem", "Aluno removido.");
        return "redirect:/alunos";
    }
}