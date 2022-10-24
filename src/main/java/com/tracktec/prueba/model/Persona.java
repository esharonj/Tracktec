package com.tracktec.prueba.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Persona {

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String aMaterno;

	@NotEmpty
	private String aPaterno;

}
