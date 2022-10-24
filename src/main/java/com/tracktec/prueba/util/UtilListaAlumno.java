package com.tracktec.prueba.util;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.tracktec.prueba.controller.AlumnoController;
import com.tracktec.prueba.model.AgregarAlumno;
import com.tracktec.prueba.model.Alumno;
import com.tracktec.prueba.model.EdicionAlumno;
import com.tracktec.prueba.model.EliminarAlumno;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Configuration
@Slf4j
public class UtilListaAlumno {
	
	public EdicionAlumno modificarAlumno(long idAlumno, Alumno alumno, List<Alumno> lista) {
		
		log.info("Se ejecuta metodo modificarAlumno");
		EdicionAlumno salida = new EdicionAlumno();
		
		log.info("Se busca registro a ser modificado");
		for (Alumno alumnos : lista) {
			
			if(alumnos.getId() == idAlumno) {
				log.info("registro a ser modificado es :" + alumnos.toString());
				alumnos.setNombre(alumno.getNombre());	
				alumnos.setAPaterno(alumno.getAPaterno());
				alumnos.setAMaterno(alumno.getAMaterno());
				alumnos.setDireccion(alumno.getDireccion());
				alumnos.setTelefono(alumno.getTelefono());
				log.info("una vez modificado : " + alumnos.toString());
				salida.setMensaje(Constantes.MODIFICACION_EXITOSA + idAlumno);
				log.info("mensaje : " + Constantes.MODIFICACION_EXITOSA + idAlumno);
				salida.setAlumnos(lista);

				return salida;
			}
			
		}

		salida.setMensaje(Constantes.MODIFICACION_FALLIDA + idAlumno);
		log.info("mensaje : " + Constantes.MODIFICACION_FALLIDA + idAlumno);
		
		salida.setAlumnos(lista);

		return null;
	}
	
	public Alumno buscarAlumno(long idAlumno, List<Alumno> lista) {
		
		log.info("Se ejecuta metodo buscarAlumno");
		
		for (Alumno alumnos : lista) {
			
			if(alumnos.getId() == idAlumno) {
				log.info("Alumno buscado es :" + alumnos.toString());
				return alumnos ;
			}
			
		}
		
		log.info("No se encontró Alumno");
		return null;
	}
	
	
	public EliminarAlumno eliminarAlumno(long idAlumno, List<Alumno> lista) {
		
		log.info("Se ejecuta metodo eliminarAlumno");
		EliminarAlumno edicion = new EliminarAlumno();
		Alumno salida = buscarAlumno(idAlumno, lista);
		
		if(salida == null) {
			log.info(Constantes.ELIMINACION_FALLIDA + idAlumno);
			edicion.setMensaje(Constantes.ELIMINACION_FALLIDA + idAlumno);
			edicion.setAlumnos(lista);
		}else {
			int registros = lista.size();
			
			lista.remove(salida);
			
			if(registros == lista.size()) {
				log.info(Constantes.ELIMINACION_FALLIDA + idAlumno);
				edicion.setMensaje(Constantes.ELIMINACION_FALLIDA + idAlumno);
				edicion.setAlumnos(lista);
			}else {
				log.info(Constantes.ELIMINACION_EXITOSA + idAlumno);
				edicion.setMensaje(Constantes.ELIMINACION_EXITOSA + idAlumno);
				edicion.setAlumnos(lista);
			}
		}
		
		return edicion;
	}
	
	
	public AgregarAlumno guardarAlumno(Alumno alumno, List<Alumno> lista) {

		log.info("Se ejecuta metodo guardarAlumno");
		AgregarAlumno salida = new AgregarAlumno();
		long mayor = 0;
		
		if(lista.size() == 0) {
			log.info("Es el primer registro el usuario :" + alumno.toString());
			
			alumno.setId(1);
			lista.add(alumno);
		}else {
			for (Alumno alumnoRevisar: lista) {
				if(mayor == 0) {
					mayor = alumnoRevisar.getId();
				}else{
					if(mayor < alumnoRevisar.getId() ){
						mayor = alumnoRevisar.getId();
					}
				}
				
			}
			alumno.setId(mayor + 1);
			log.info("Es el registro " + (mayor + 1) +" el usuario :" + alumno.toString());
			lista.add(alumno);
		}
		
		salida.setAlumno(alumno);
		salida.setLista(lista);
		
		return salida;
	}
	
	
}
