package com.mundial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntrenadorWebControlador {

    // Pagina de entrenadores: http://localhost:PUERTO/entrenadores
    @GetMapping("/entrenadores")
    public String entrenadores(Model model) {
        return HomeControlador.pagina(model, "entrenadores");
    }
}
