package com.b2w.starwars.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeletePlanetResponse implements Serializable{

	private static final long serialVersionUID = -29537643840588887L;
	
	private String id;
	private String message;
}
