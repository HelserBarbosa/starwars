package com.b2w.starwars.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionBody {

	private String message;
	private LocalDateTime time;
	
}
