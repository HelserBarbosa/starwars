package com.b2w.starwars.integration.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PlanetResponse implements Serializable {

	private static final long serialVersionUID = -2422036809874703704L;

	@JsonProperty("count")
	private Long count;
	
	@JsonProperty("next")
	private String nextPage;
	
	@JsonProperty("previous")
	private String previousPage;
	
	@JsonProperty("results")
	private List<Planet> planets;

}
