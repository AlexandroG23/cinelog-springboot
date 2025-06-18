package com.aasencios.springbootcinelogtmdb.controller;

import com.aasencios.springbootcinelogtmdb.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String formularioRegistro(){
        return "registro"; // Thymeleaf buscara el archivo registro.html en la carpeta /templates
    }

    @GetMapping("/login")
    public String formularioLogin(){
        return "login"; // Thymeleaf buscara el archivo login.html en la carpeta /templates
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, Model model){
        try {
            usuarioService.registrar(nombre, email, password);
            return "redirect:/usuario/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registro"; // Thymeleaf buscara el archivo registro.html en la carpeta /templates
        }
    }
}
