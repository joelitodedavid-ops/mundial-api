package com.mundial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClubWebControlador {

    // Pagina de clubes: http://localhost:PUERTO/clubes
    @GetMapping("/clubes")
    public String clubes(Model model) {
        return HomeControlador.pagina(model, "clubes");
    }
}