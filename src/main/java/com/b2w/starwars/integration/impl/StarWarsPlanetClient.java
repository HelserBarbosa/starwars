package com.b2w.starwars.integration.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.b2w.starwars.integration.model.PlanetResponse;

@FeignClient(name = "${feign.name.planets}", url = "${feign.planets.url}")
public interface StarWarsPlanetClient {

	@RequestMapping(method = RequestMethod.GET)
	PlanetResponse getPlanets(@RequestParam("page") Long page,@RequestParam("search") String name);
}
