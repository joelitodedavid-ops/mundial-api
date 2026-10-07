package com.mundial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mundial.entidades.Entrenador;

public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {

}
