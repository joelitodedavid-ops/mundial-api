package com.mundial.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundial.entidades.Club;
import com.mundial.entidades.Jugador;
import com.mundial.repository.ClubRepository;
import com.mundial.repository.JugadorRepository;

@RestController
@RequestMapping("/api/clubes")
@CrossOrigin(origins = "*")
public class ClubRestControlador {

    private final ClubRepository repo;
    private final JugadorRepository jugadorRepo;

    public ClubRestControlador(ClubRepository repo, JugadorRepository jugadorRepo) {
        this.repo = repo;
        this.jugadorRepo = jugadorRepo;
    }

    // GET /api/clubes  -> lista todos (con entrenador, jugadores, etc. incluidos)
    @GetMapping
    public List<Club> listar() {
        return repo.findAll();
    }

    // GET /api/clubes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Club> obtener(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/clubes/buscar?nombre=brasil
    @GetMapping("/buscar")
    public List<Club> buscar(@RequestParam String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    // POST /api/clubes
    // Las referencias se envian solo con el id: "entrenador": {"id": "..."}
    @PostMapping
    public ResponseEntity<Club> crear(@RequestBody Club nuevo) {
        nuevo.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(nuevo));
    }

    // PUT /api/clubes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Club> actualizar(@PathVariable String id, @RequestBody Club datos) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        datos.setId(id);
        return ResponseEntity.ok(repo.save(datos));
    }

    // DELETE /api/clubes/{id}  (no borra entrenador, jugadores ni competiciones)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/clubes/{clubId}/jugadores/{jugadorId}  -> agrega un jugador al club
    @PostMapping("/{clubId}/jugadores/{jugadorId}")
    public ResponseEntity<Club> agregarJugador(@PathVariable String clubId, @PathVariable String jugadorId) {
        Optional<Club> club = repo.findById(clubId);
        Optional<Jugador> jugador = jugadorRepo.findById(jugadorId);
        if (club.isEmpty() || jugador.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Club c = club.get();
        if (c.getJugadores() == null) {
            c.setJugadores(new ArrayList<>());
        }
        boolean yaEsta = c.getJugadores().stream()
                .anyMatch(j -> j != null && jugadorId.equals(j.getId()));
        if (!yaEsta) {
            c.getJugadores().add(jugador.get());
        }
        return ResponseEntity.ok(repo.save(c));
    }

    // DELETE /api/clubes/{clubId}/jugadores/{jugadorId}  -> quita un jugador del club
    @DeleteMapping("/{clubId}/jugadores/{jugadorId}")
    public ResponseEntity<Club> quitarJugador(@PathVariable String clubId, @PathVariable String jugadorId) {
        Optional<Club> club = repo.findById(clubId);
        if (club.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Club c = club.get();
        if (c.getJugadores() != null) {
            c.getJugadores().removeIf(j -> j == null || jugadorId.equals(j.getId()));
        }
        return ResponseEntity.ok(repo.save(c));
    }
}
