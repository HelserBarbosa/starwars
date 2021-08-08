package com.b2w.starwars.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class PlanetDomain implements Serializable {
	
	private static final long serialVersionUID = 2903251547013186880L;
	
	@Id
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private Integer numberOfMovies;

}