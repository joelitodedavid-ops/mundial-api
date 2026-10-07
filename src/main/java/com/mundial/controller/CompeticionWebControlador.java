package com.mundial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompeticionWebControlador {

    // Pagina de competiciones: http://localhost:PUERTO/competiciones
    @GetMapping("/competiciones")
    public String competiciones(Model model) {
        return HomeControlador.pagina(model, "competiciones");
    }
}