package com.paulacrespo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * 
	CREATE TABLE socio(
	    id_socio INT,
	    dni VARCHAR(9),
	    nombre VARCHAR(30),
	    fechaNacimiento DATE,
	    pelicula INT,
	    PRIMARY KEY(id_socio),
	    FOREIGN KEY(pelicula) REFERENCES peliculas(id_pelicula)
	);
 * 
 **/

@Data
@Entity
@Table(name = "socio")
public class Socio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fechaNacimiento")
	private int fechaNacimiento;
	
	@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "socio")
	@ToString.Exclude
	private Pelicula pelicula;
	
	@ManyToOne
	@JoinColumn(name = "idVideoclub")
	@ToString.Exclude
	private Videoclub vc;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
}
