package com.tracktec.prueba.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracktec.prueba.model.AgregarAlumno;
import com.tracktec.prueba.model.Alumno;
import com.tracktec.prueba.model.EdicionAlumno;
import com.tracktec.prueba.model.EliminarAlumno;
import com.tracktec.prueba.service.AlumnoListaService;
import com.tracktec.prueba.util.UtilListaAlumno;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlumnoListaServiceImpl implements AlumnoListaService{

	@Autowired
	public UtilListaAlumno utilLista;
  
	@Override
	public List<Alumno> guardarAlumno(Alumno alumno, List<Alumno> lista) {
		lista.add(alumno);
		return lista;
	}

	@Override
	public EdicionAlumno modificarAlumno(long idAlumno, Alumno alumno, List<Alumno> lista) {
		
		return utilLista.modificarAlumno(idAlumno, alumno, lista);
		
	}

	@Override
	public Alumno buscarAlumno(long idAlumno, List<Alumno> lista) {
		return utilLista.buscarAlumno(idAlumno, lista);
	}

	@Override
	public EliminarAlumno eliminar(long idAlumno, List<Alumno> lista) {
		return utilLista.eliminarAlumno(idAlumno, lista);
	}

	@Override
	public AgregarAlumno agregarAlumno(Alumno alumno, List<Alumno> lista) {
		return utilLista.guardarAlumno(alumno, lista);
	}

}
