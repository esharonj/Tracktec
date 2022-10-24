package com.tracktec.prueba.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EliminarAlumno {

	private String mensaje;
	private List<Alumno> alumnos;
	
}
