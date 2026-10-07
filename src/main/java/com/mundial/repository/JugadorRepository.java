package com.mundial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mundial.entidades.Jugador;

public interface JugadorRepository extends MongoRepository<Jugador, String> {

    List<Jugador> findByNombreContainingIgnoreCase(String nombre);

    List<Jugador> findByPosicionIgnoreCase(String posicion);
}
