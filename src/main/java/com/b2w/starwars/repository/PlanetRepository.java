package com.b2w.starwars.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.b2w.starwars.domain.PlanetDomain;

@Repository
public interface PlanetRepository extends MongoRepository<PlanetDomain, String> {

	List<PlanetDomain> findAllByNome(String nome);
	
}
