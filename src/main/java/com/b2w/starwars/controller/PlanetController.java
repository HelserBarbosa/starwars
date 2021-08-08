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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("planets")
@Tag(name = "Planets", description = "api de planetas")
public class PlanetController {

	@Autowired
	private PlanetService service;

	@Operation(summary = "Consulta de planetas pela api informada no teste")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PlanetResponse.class)))) })
	@GetMapping
	public ResponseEntity<PlanetResponse> getPlanets(@RequestParam(value = "page", required = false) Long page,
			@RequestParam(value = "name", required = false) String name) {
		return ResponseEntity.ok(this.service.getStarWarsPlanets(page, name));
	}

	@Operation(summary = "Criação de planetas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PlanetDomain.class)))) })
	@PostMapping
	public ResponseEntity<PlanetDomain> postPlanet(
			@Parameter(required = true, schema = @Schema(implementation = PlanetDomain.class)) @RequestBody PlanetDomain planet) {
		return new ResponseEntity<PlanetDomain>(this.service.postPlanet(planet), HttpStatus.CREATED);
	}

	@Operation(summary = "Consulta de planetas pelo id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PlanetDomain.class)))) })
	@GetMapping("{id}")
	public ResponseEntity<PlanetDomain> getPlanets(@PathVariable("id") String id) {
		return ResponseEntity.ok(this.service.getPlanet(id));
	}

	@Operation(summary = "Listagem de planetas pelo nome")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PlanetDomain.class)))) })
	@GetMapping("list")
	public ResponseEntity<Page<PlanetDomain>> getPlanets(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "name", required = false) String name) {
		return ResponseEntity.ok(this.service.getPlanets(name, page, size));
	}

}
