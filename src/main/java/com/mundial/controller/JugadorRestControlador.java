package com.mundial.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundial.entidades.Jugador;
import com.mundial.repository.JugadorRepository;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
public class JugadorRestControlador {

    private final JugadorRepository repo;

    public JugadorRestControlador(JugadorRepository repo) {
        this.repo = repo;
    }

    // GET /api/jugadores  -> lista todos
    @GetMapping
    public List<Jugador> listar() {
        return repo.findAll();
    }

    // GET /api/jugadores/{id}  -> busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtener(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/jugadores  -> crea (MongoDB genera el id)
    @PostMapping
    public ResponseEntity<Jugador> crear(@RequestBody Jugador nuevo) {
        nuevo.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(nuevo));
    }

    // PUT /api/jugadores/{id}  -> actualiza
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizar(@PathVariable String id, @RequestBody Jugador datos) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        datos.setId(id);
        return ResponseEntity.ok(repo.save(datos));
    }

    // DELETE /api/jugadores/{id}  -> elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/jugadores/buscar?nombre=messi
    @GetMapping("/buscar")
    public List<Jugador> buscar(@RequestParam String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    // GET /api/jugadores/posicion/Delantero
    @GetMapping("/posicion/{posicion}")
    public List<Jugador> porPosicion(@PathVariable String posicion) {
        return repo.findByPosicionIgnoreCase(posicion);
    }
}