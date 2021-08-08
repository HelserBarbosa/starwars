package com.b2w.starwars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.b2w.starwars.domain.PlanetDomain;
import com.b2w.starwars.exception.StarWarsException;
import com.b2w.starwars.integration.impl.StarWarsPlanetClient;
import com.b2w.starwars.integration.model.Planet;
import com.b2w.starwars.integration.model.PlanetResponse;
import com.b2w.starwars.repository.PlanetRepository;
import com.b2w.starwars.services.PlanetService;

@RunWith(MockitoJUnitRunner.class)
class PlanetServiceTest {

	private static final String TESTE = "Teste";

	@Mock
	private StarWarsPlanetClient starWarsPlanetClient;

	@Mock
	private PlanetRepository repository;

	@InjectMocks
	private PlanetService service;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void postPlanetTest() {

		PlanetDomain planetDomain = new PlanetDomain();
		planetDomain.setNome(TESTE);
		Mockito.when(this.starWarsPlanetClient.getPlanets(null, TESTE)).thenReturn(this.getPlanetResponse());
		Mockito.when(this.repository.save(planetDomain)).thenReturn(planetDomain);

		assertNotNull(this.service.postPlanet(planetDomain));
	}
 
	@Test
	public void postPlanetExceptionTest() {
		PlanetDomain planetDomain = new PlanetDomain();
		planetDomain.setNome(TESTE);
		Mockito.when(this.starWarsPlanetClient.getPlanets(null, TESTE)).thenReturn(this.getPlanetResponseEmpty());
		Mockito.when(this.repository.save(planetDomain)).thenReturn(planetDomain);

		assertThrows(StarWarsException.class, () -> this.service.postPlanet(planetDomain));
	}

	@Test
	public void getStarWarsPlanetsTest() {
		Mockito.when(this.starWarsPlanetClient.getPlanets(null, TESTE)).thenReturn(this.getPlanetResponse());
		assertEquals(this.service.getStarWarsPlanets(null, TESTE).getPlanets().get(0).getName(), TESTE);
	}

	@Test
	public void getPlanetTest() {
		Optional<PlanetDomain> planet = Optional.of(this.getPlanetDomain());
		Mockito.when(this.repository.findById(TESTE)).thenReturn(planet);
		assertEquals(this.service.getPlanet(TESTE).getNome(), TESTE);
	}

	@Test
	public void getPlanetExceptionTest() {
		Optional<PlanetDomain> planet = Optional.empty();
		Mockito.when(this.repository.findById(TESTE)).thenReturn(planet);
		assertThrows(StarWarsException.class, () -> this.service.getPlanet(TESTE));
	}
	
	@Test
	public void getPlanetsTest() {
		Page<PlanetDomain> page = Page.empty();
		Mockito.when(this.repository.findByNome(TESTE, PageRequest.of(1, 10))).thenReturn(page);
		assertEquals(this.service.getPlanets(TESTE, 1, 10).getContent(), page.getContent());
	}

	@Test
	public void removePlanetTest() {
		Mockito.when(this.repository.existsById(TESTE)).thenReturn(true);
		assertNotNull(this.service.removePlanet(TESTE));
		
	}

	@Test
	public void removePlaneExceptiontTest() {
		Mockito.when(this.repository.existsById(TESTE)).thenReturn(false);
		assertThrows(StarWarsException.class, () -> this.service.removePlanet(TESTE));
	}

	private PlanetResponse getPlanetResponse() {
		PlanetResponse planetResponse = new PlanetResponse();
		planetResponse.setPlanets(this.getPlanets());
		return planetResponse;
	}

	private PlanetResponse getPlanetResponseEmpty() {
		PlanetResponse planetResponse = new PlanetResponse();
		planetResponse.setPlanets(new ArrayList<Planet>());
		return planetResponse;
	}

	private List<Planet> getPlanets() {
		List<Planet> planets = new ArrayList<Planet>();
		Planet planet = new Planet();
		planet.setName(TESTE);
		List<String> films = new ArrayList<String>();
		planet.setFilms(films);
		planets.add(planet);
		return planets;
	}

	private PlanetDomain getPlanetDomain() {
		PlanetDomain planetDomain = new PlanetDomain();
		planetDomain.setNome(TESTE);
		return planetDomain;
	}

}
