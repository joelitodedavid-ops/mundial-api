package com.mundial.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundial.entidades.Entrenador;
import com.mundial.repository.EntrenadorRepository;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
public class EntrenadorRestControlador {

    private final EntrenadorRepository repo;

    public EntrenadorRestControlador(EntrenadorRepository repo) {
        this.repo = repo;
    }

    // GET /api/entrenadores  -> lista todos
    @GetMapping
    public List<Entrenador> listar() {
        return repo.findAll();
    }

    // GET /api/entrenadores/{id}  -> busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtener(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/entrenadores  -> crea (MongoDB genera el id)
    @PostMapping
    public ResponseEntity<Entrenador> crear(@RequestBody Entrenador nuevo) {
        nuevo.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(nuevo));
    }

    // PUT /api/entrenadores/{id}  -> actualiza
    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizar(@PathVariable String id, @RequestBody Entrenador datos) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        datos.setId(id);
        return ResponseEntity.ok(repo.save(datos));
    }

    // DELETE /api/entrenadores/{id}  -> elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}