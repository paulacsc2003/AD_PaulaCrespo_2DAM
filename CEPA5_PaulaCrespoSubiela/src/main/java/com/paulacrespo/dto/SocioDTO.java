package com.paulacrespo.dto;

import java.io.Serializable;
import com.paulacrespo.model.Socio;

import lombok.Data;

@Data
public class SocioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long idSocio;
	private String dni;
	private int fechaNacimiento;
	
	private PeliculaDTO pelicula;
	private VideoclubDTO vcdto;

	public static SocioDTO convertToDTO (Socio socio, VideoclubDTO vc) {
		SocioDTO socioDTO = new SocioDTO();
		socioDTO.setIdSocio(socio.getId());
		socioDTO.setDni(socio.getDni());
		socioDTO.setFechaNacimiento(socio.getFechaNacimiento());
		socioDTO.setPelicula(PeliculaDTO.convertToDTO(socio.getPelicula(), socioDTO));
		socioDTO.setVcdto(vc);
		return socioDTO;		
	}
	
	public static Socio convertToEntity(SocioDTO sociodto) {
		
		Socio socio = new Socio();
		socio.setId(sociodto.getIdSocio());
		socio.setDni(sociodto.getDni());
		socio.setFechaNacimiento(sociodto.getFechaNacimiento());
		socio.setPelicula(PeliculaDTO.convertToEntity(sociodto.getPelicula(), socio));
		return socio;
		
	}
	
	public SocioDTO() {
		super();
		this.pelicula = new PeliculaDTO();
	}
	
}
