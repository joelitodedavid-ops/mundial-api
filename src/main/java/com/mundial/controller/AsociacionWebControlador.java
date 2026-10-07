package com.mundial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AsociacionWebControlador {

    // Pagina de asociaciones: http://localhost:PUERTO/asociaciones
    @GetMapping("/asociaciones")
    public String asociaciones(Model model) {
        return HomeControlador.pagina(model, "asociaciones");
    }
}