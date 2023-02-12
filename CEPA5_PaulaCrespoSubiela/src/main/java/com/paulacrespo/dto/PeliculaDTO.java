package com.paulacrespo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.paulacrespo.model.Pelicula;
import com.paulacrespo.model.Socio;

import lombok.Data;
import lombok.ToString;

@Data
public class PeliculaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String titulo;
	private int añoLanzamiento;
	private String sinopsis;
	@ToString.Exclude
	@JsonBackReference
	private SocioDTO sociodto;
	
	public	static PeliculaDTO convertToDTO (Pelicula pelicula, SocioDTO socio) {
		
		PeliculaDTO peliculaDTO = new PeliculaDTO(); 
		peliculaDTO.setId(pelicula.getId());		
		peliculaDTO.setTitulo(pelicula.getTitulo());
		peliculaDTO.setAñoLanzamiento(pelicula.getAñoLanzamiento());
		peliculaDTO.setSinopsis(pelicula.getSinopsis());
		peliculaDTO.setSociodto(socio);
		
		return peliculaDTO;
		
	}
	
	public static Pelicula convertToEntity (PeliculaDTO peliculadto, Socio socio) {
		
		Pelicula pelicula = new Pelicula(); 
		pelicula.setId(peliculadto.getId());		
		pelicula.setTitulo(peliculadto.getTitulo());
		pelicula.setAñoLanzamiento(peliculadto.getAñoLanzamiento());
		pelicula.setSinopsis(peliculadto.getSinopsis());
		pelicula.setSocio(socio);
		
		return pelicula;
		
	}
	
}
