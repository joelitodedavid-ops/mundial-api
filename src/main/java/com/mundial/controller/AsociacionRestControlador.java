package com.mundial.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundial.entidades.Asociacion;
import com.mundial.repository.AsociacionRepository;

@RestController
@RequestMapping("/api/asociaciones")
@CrossOrigin(origins = "*")
public class AsociacionRestControlador {

    private final AsociacionRepository repo;

    public AsociacionRestControlador(AsociacionRepository repo) {
        this.repo = repo;
    }

    // GET /api/asociaciones  -> lista todos
    @GetMapping
    public List<Asociacion> listar() {
        return repo.findAll();
    }

    // GET /api/asociaciones/{id}  -> busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Asociacion> obtener(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/asociaciones  -> crea (MongoDB genera el id)
    @PostMapping
    public ResponseEntity<Asociacion> crear(@RequestBody Asociacion nuevo) {
        nuevo.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(nuevo));
    }

    // PUT /api/asociaciones/{id}  -> actualiza
    @PutMapping("/{id}")
    public ResponseEntity<Asociacion> actualizar(@PathVariable String id, @RequestBody Asociacion datos) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        datos.setId(id);
        return ResponseEntity.ok(repo.save(datos));
    }

    // DELETE /api/asociaciones/{id}  -> elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}