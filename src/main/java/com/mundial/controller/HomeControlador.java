package com.mundial.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {

    // Pagina de inicio: http://localhost:PUERTO/
    @GetMapping("/")
    public String inicio(Model model) {
        return pagina(model, "clubes");
    }

    // Prepara los datos de index.html. Lo usan este controlador y los 5 controladores web.
    public static String pagina(Model model, String entidad) {
        // clave = nombre de la entidad, valor = texto del menu
        Map<String, String> entidades = new LinkedHashMap<>();
        entidades.put("clubes", "Clubes");
        entidades.put("jugadores", "Jugadores");
        entidades.put("entrenadores", "Entrenadores");
        entidades.put("asociaciones", "Asociaciones");
        entidades.put("competiciones", "Competiciones");

        model.addAttribute("titulo", "Gesti\u00f3n del Mundial");
        model.addAttribute("entidades", entidades);
        model.addAttribute("entidad", entidad);
        return "index";
    }
}