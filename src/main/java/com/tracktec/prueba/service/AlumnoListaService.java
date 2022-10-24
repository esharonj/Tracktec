package com.tracktec.prueba.service;

import java.util.List;

import com.tracktec.prueba.model.AgregarAlumno;
import com.tracktec.prueba.model.Alumno;
import com.tracktec.prueba.model.EdicionAlumno;
import com.tracktec.prueba.model.EliminarAlumno;

public interface AlumnoListaService {

	List<Alumno> guardarAlumno(Alumno alumno, List<Alumno> lista);

	EdicionAlumno modificarAlumno(long idAlumno, Alumno alumno, List<Alumno> lista);

	Alumno buscarAlumno(long idAlumno, List<Alumno> lista);

	EliminarAlumno eliminar(long idAlumno, List<Alumno> lista);
	
	AgregarAlumno agregarAlumno(Alumno alumno, List<Alumno> lista); 
	
}
