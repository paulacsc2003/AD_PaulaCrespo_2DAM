package com.paulacrespo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import lombok.Data;
import lombok.ToString;

/**
 * 
	CREATE TABLE peliculas(
	    id_pelicula INT,
	    titulo VARCHAR(50),
	    añoLanzamiento INT,
	    sinopsis VARCHAR(100),
	    PRIMARY KEY(id_pelicula)
	);
 * 
 **/

@Data
@Entity
@Table(name = "peliculas")
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "añoLanzamiento")
	private int añoLanzamiento;
	
	@Column(name = "sinopsis")
	private String sinopsis;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_socio")
	@ToString.Exclude
	private Socio socio;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
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
