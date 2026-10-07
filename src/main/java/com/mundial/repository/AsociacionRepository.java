package com.mundial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mundial.entidades.Asociacion;

public interface AsociacionRepository extends MongoRepository<Asociacion, String> {

}
