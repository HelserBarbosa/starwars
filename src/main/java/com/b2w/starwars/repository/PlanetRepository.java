package com.b2w.starwars.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.b2w.starwars.domain.PlanetDomain;

@Repository
public interface PlanetRepository extends MongoRepository<PlanetDomain, String> {

	Page<PlanetDomain> findByNome(String nome, PageRequest page);
	
}
