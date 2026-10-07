package com.mundial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mundial.entidades.Competicion;

public interface CompeticionRepository extends MongoRepository<Competicion, String> {

}
