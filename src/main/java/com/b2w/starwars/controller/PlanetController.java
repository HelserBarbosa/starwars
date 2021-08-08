package com.b2w.starwars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.b2w.starwars.domain.PlanetDomain;
import com.b2w.starwars.integration.model.PlanetResponse;
import com.b2w.starwars.services.PlanetService;

@Controller
@RequestMapping("planets")
public class PlanetController {

	@Autowired
	private PlanetService service;

	@GetMapping
	public ResponseEntity<PlanetResponse> getPlanets(@RequestParam(value = "page", required = false) Long page,
			@RequestParam(value = "name", required = false) String name) {
		return ResponseEntity.ok(this.service.getStarWarsPlanets(page, name));
	}

	@PostMapping
	public ResponseEntity<PlanetDomain> postPlanet(@RequestBody PlanetDomain planet) {
		return new ResponseEntity<PlanetDomain>(this.service.postPlanet(planet), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<PlanetDomain> getPlanets(@PathVariable("id") String id) {
		return ResponseEntity.ok(this.service.getPlanet(id));
	}

	@GetMapping("list")
	public ResponseEntity<Page<PlanetDomain>> getPlanets(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "name", required = false) String name) {
		return ResponseEntity.ok(this.service.getPlanets(name, page, size));
	}

}
