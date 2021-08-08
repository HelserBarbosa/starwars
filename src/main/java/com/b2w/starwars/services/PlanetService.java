package com.b2w.starwars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.b2w.starwars.domain.PlanetDomain;
import com.b2w.starwars.dto.DeletePlanetResponse;
import com.b2w.starwars.exception.StarWarsException;
import com.b2w.starwars.integration.impl.StarWarsPlanetClient;
import com.b2w.starwars.integration.model.PlanetResponse;
import com.b2w.starwars.repository.PlanetRepository;

@Service
public class PlanetService {

	private static final String PLANETA_REMOVIDO_COM_SUCESSO = "Planeta removido com sucesso";
	// TODO mensagem properties
	private static final String PLANETA_NÃO_ENCONTRADO = "Planeta não encontrado";
	private static final String PLANETA_NÃO_EXISTE_NA_API_STAR_WARS = "Planeta não existe na api Star Wars";

	@Autowired
	private StarWarsPlanetClient starWarsPlanetClient;

	@Autowired
	private PlanetRepository repository; 

	public PlanetResponse getStarWarsPlanets(Long page, String name) {
		return this.starWarsPlanetClient.getPlanets(page, name);
	}

	public PlanetDomain postPlanet(PlanetDomain planet) {

		if (getStarWarsPlanets(null, planet.getNome()).getPlanets().size() <= 0) {
			throw new StarWarsException(PLANETA_NÃO_EXISTE_NA_API_STAR_WARS);
		}
		planet.setNumberOfMovies(getStarWarsPlanets(null, planet.getNome()).getPlanets().get(0).getFilms().size());
		return this.repository.save(planet);
	}

	public PlanetDomain getPlanet(String id) {
		return this.repository.findById(id).orElseThrow(() -> new StarWarsException(PLANETA_NÃO_ENCONTRADO));
	}

	public Page<PlanetDomain> getPlanets(String name, Integer page, Integer size) {
		return this.repository.findByNome(name, PageRequest.of(page, size));
	}

	public DeletePlanetResponse removePlanet(String id) {
		if (!this.repository.existsById(id)) {
			throw new StarWarsException(PLANETA_NÃO_ENCONTRADO);
		}
		this.repository.deleteById(id);
		return DeletePlanetResponse.builder().id(id).message(PLANETA_REMOVIDO_COM_SUCESSO).build();
	}
}
