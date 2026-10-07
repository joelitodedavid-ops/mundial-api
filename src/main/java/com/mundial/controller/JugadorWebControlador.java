package com.mundial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JugadorWebControlador {

    // Pagina de jugadores: http://localhost:PUERTO/jugadores
    @GetMapping("/jugadores")
    public String jugadores(Model model) {
        return HomeControlador.pagina(model, "jugadores");
    }
}