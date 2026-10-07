package com.mundial.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundial.entidades.Competicion;
import com.mundial.repository.CompeticionRepository;

@RestController
@RequestMapping("/api/competiciones")
@CrossOrigin(origins = "*")
public class CompeticionRestControlador {

    private final CompeticionRepository repo;

    public CompeticionRestControlador(CompeticionRepository repo) {
        this.repo = repo;
    }

    // GET /api/competiciones  -> lista todos
    @GetMapping
    public List<Competicion> listar() {
        return repo.findAll();
    }

    // GET /api/competiciones/{id}  -> busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Competicion> obtener(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/competiciones  -> crea (MongoDB genera el id)
    @PostMapping
    public ResponseEntity<Competicion> crear(@RequestBody Competicion nuevo) {
        nuevo.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(nuevo));
    }

    // PUT /api/competiciones/{id}  -> actualiza
    @PutMapping("/{id}")
    public ResponseEntity<Competicion> actualizar(@PathVariable String id, @RequestBody Competicion datos) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        datos.setId(id);
        return ResponseEntity.ok(repo.save(datos));
    }

    // DELETE /api/competiciones/{id}  -> elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}