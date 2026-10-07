package com.mundial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mundial.entidades.Club;

public interface ClubRepository extends MongoRepository<Club, String> {

    List<Club> findByNombreContainingIgnoreCase(String nombre);
}
