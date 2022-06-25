package br.senai.controller;

import br.senai.model.Cliente;
import br.senai.model.Usuario;
import br.senai.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro/save")
    public String save(Usuario usuario, Model model){
        try{
            usuarioService.save(usuario);
            model.addAttribute("usuario", usuario);
            model.addAttribute("isSave", true);
            return "redirect:/";
        }catch (Exception e){
            model.addAttribute("usuario", usuario);
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", e.getMessage());
            return "/cadastro";
        }
    }

    @GetMapping("/sobre")
    public String sobre(){
        return "sobre";
    }
}
