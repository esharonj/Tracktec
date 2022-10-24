package com.tracktec.prueba.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracktec.prueba.model.AgregarAlumno;
import com.tracktec.prueba.model.Alumno;
import com.tracktec.prueba.model.EdicionAlumno;
import com.tracktec.prueba.model.EliminarAlumno;
import com.tracktec.prueba.model.ListaAlumnoresponse;
import com.tracktec.prueba.service.AlumnoListaService;
import com.tracktec.prueba.util.Constantes;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/tracktec/api-alumno")
@Slf4j 
public class AlumnoController {

	@Autowired
	private AlumnoListaService alumnoService;

	public List<Alumno> alumnos = new ArrayList<Alumno>();

	@SuppressWarnings("unchecked")
	@GetMapping("/buscarAlumno/{id}")
	public ResponseEntity buscarAlumno(@PathVariable(value = "id") long id) {

		log.info("Se ejecuta servicio buscarAlumno, con el Id :" + id);
		
		try {

			Alumno alumno = alumnoService.buscarAlumno(id, this.alumnos);
			
			if(alumno == null) {
				log.info(Constantes.BUSQUEDA_FALLIDA + id);
				return new ResponseEntity(Constantes.BUSQUEDA_FALLIDA + id , HttpStatus.OK);
			}else {
				log.info("Usuario encontrado" + alumno.toString() );
				return new ResponseEntity(alumno, HttpStatus.OK);
			}
			

		} catch (Exception e) {
			log.error("Usuario no encontrado");
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/listarAlumnos")
	public ResponseEntity listarAlumnos() {
		
		log.info("Se ejecuta servicio listarAlumnos");
		
		ListaAlumnoresponse respuestaService = new ListaAlumnoresponse();

		try {

				if(this.alumnos.size() == 0) {
					log.info("No existen registros");
					return new ResponseEntity("No existen registros" , HttpStatus.OK);
				}else {
					log.info("Se listan los " + this.alumnos.size() + " registros encontrados :" + this.alumnos.toString());
					return new ResponseEntity(this.alumnos, HttpStatus.OK);
				}

		} catch (Exception e) {
			log.error("Se produjo un error al intentar leer los registros de Alumnos");
			return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@PostMapping("/guardarAlumno")
	public ResponseEntity<Alumno> guardarAlumno(@RequestBody  Alumno alumno){

		log.info("Se ejecuta servicio guardarAlumno");

		try {
			
			log.info("Se agrega el Alumno :" + alumno.toString());
			AgregarAlumno agregar = alumnoService.agregarAlumno(alumno, this.alumnos);
			this.alumnos = agregar.getLista();
			return new ResponseEntity<Alumno>(alumno, HttpStatus.CREATED);

		} catch (Exception e) {
			log.info("No fue posible el poder ingresar el nuevo Alumno");
			return new ResponseEntity<Alumno>(alumno, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
		}
	}

	@PutMapping("/actualizarAlumno/{id}")
	public ResponseEntity<String> actualizarAlumno(@PathVariable(value = "id") Long id,
			@RequestBody Alumno alumnoDetalle) {

		log.info("Se ejecuta servicio actualizarAlumno");
		
		EdicionAlumno alumno = alumnoService.modificarAlumno(id, alumnoDetalle, this.alumnos);

		try {
			log.info(alumno.getMensaje());
			return new ResponseEntity<String>(alumno.getMensaje(), HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Error de ejecución del servicio");
			return new ResponseEntity<String>("Error de ejecución del servicio", HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
		}
	}

	
	@DeleteMapping("/eliminarAlumno/{id}")
	public ResponseEntity<String> eliminarAlumno(@PathVariable(value = "id") Long id){

		log.info("Se ejecuta servicio eliminarAlumno");
		EliminarAlumno alumno = alumnoService.eliminar(id, alumnos);

		try {
			log.info(alumno.getMensaje());
			return new ResponseEntity<String>(alumno.getMensaje(), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.info("Error de ejecución del servicio");
			return new ResponseEntity<String>("Error de ejecución del servicio", HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
		}
	}
	
	
}
