package com.tracktec.prueba.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Alumno extends Persona {

	@NotNull
	private long id;

	@NotEmpty 
	private String telefono;

	@NotEmpty
	private String direccion;

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", telefono=" + telefono + ", direccion=" + direccion + ", Nombre="
				+ getNombre() + ", AMaterno=" + getAMaterno() + ", APaterno=" + getAPaterno() + "]";
	}

	
	
}
