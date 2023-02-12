package com.paulacrespo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.paulacrespo.model.Socio;
import com.paulacrespo.model.Videoclub;

import lombok.Data;
import lombok.ToString;

@Data
public class VideoclubDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long idVideoclub;
	private String nombre;
	private String direccion;
	
	@ToString.Exclude
	@JsonManagedReference("listaSocios")
	private List<SocioDTO> listaSocios = new ArrayList<>();
	
	public static VideoclubDTO convertToDTO (Videoclub vc) {
		VideoclubDTO vcDTO = new VideoclubDTO();
		vcDTO.setIdVideoclub(vc.getId());
		vcDTO.setNombre(vc.getNombre());
		vcDTO.setDireccion(vc.getDireccion());
		
		for (Socio laSocio:vc.getListaSocios()) {
			SocioDTO sociodto = SocioDTO.convertToDTO(laSocio, vcDTO);
			vcDTO.getListaSocios().add(sociodto);
		}
		
		return vcDTO;		
	}
	
	public static Videoclub convertToEntity(VideoclubDTO vcdto) {
		
		Videoclub vc = new Videoclub();
		vc.setId(vcdto.getIdVideoclub());
		vc.setNombre(vcdto.getNombre());
		vc.setDireccion(vcdto.getDireccion());
		
		for(int i=0;i<vcdto.getListaSocios().size();i++) {
			Socio socio = SocioDTO.convertToEntity(vcdto.getListaSocios().get(i));
			vc.getListaSocios().add(socio);	
		}
		
		return vc;
		
	}
	
	public VideoclubDTO() {
		super();
		this.listaSocios = new ArrayList<SocioDTO>();
	}
	
}
