package com.b2w.starwars.integration.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Planet implements Serializable {

	private static final long serialVersionUID = -4078836627186294014L;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("orbital_period")
	private String orbitalPeriod;
	
	@JsonProperty("rotation_period")
	private String rotatioPeriod;
	
	@JsonProperty("diameter")
	private String diameter;
	
	@JsonProperty("climate")
	private String climate;
	
	@JsonProperty("gravity")
	private String gravity;
	
	@JsonProperty("terrain")
	private String terrain;
	
	@JsonProperty("surface_water")
	private String surfaceWater;
	
	@JsonProperty("population")
	private String population;
	
	@JsonProperty("residents")
	private List<String> residents;
	
	@JsonProperty("films")
	private List<String> films;
	
	@JsonProperty("created")
	private String created;
	
	@JsonProperty("edited")
	private String edited;
	
	@JsonProperty("url")
	private String url;
}
