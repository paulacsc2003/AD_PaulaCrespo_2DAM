package com.paulacrespo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulacrespo.dto.SocioDTO;
import com.paulacrespo.model.Socio;
import com.paulacrespo.repository.SocioRepository;

@Service
public class SocioServiceImpl implements SocioService {

    @Autowired
    private SocioRepository socioRepository;

    @PersistenceContext
	private EntityManager entityManager;

    @Override
    public SocioDTO saveSocio(SocioDTO socioDTO) {
        Socio socio = SocioDTO.convertToEntity(socioDTO);
        Socio newSocio = socioRepository.save(socio);
        return SocioDTO.convertToDTO(newSocio, null);
    }

    @Override
    public SocioDTO getSocioById(Long id) {

        Optional<Socio> socio = socioRepository.findById(id);
		if(socio.isPresent()) {
			return SocioDTO.convertToDTO(socio.get(), null);
		} else {
			return null;			
		}
    }

    @Override
    public SocioDTO getSocioByNombre(String nombre) {
        Optional<Socio> socio = socioRepository.findByNombre(nombre);
		if(socio.isPresent()) {
			return SocioDTO.convertToDTO(socio.get(), null);
		} else {
			return null;			
		}
    }

    @Override
    public SocioDTO getSocioByNombreDNI(String nombre, String dni) {
        Optional<Socio> socio = socioRepository.findByNombreDNI(nombre, dni);
		if(socio.isPresent()) {
			return SocioDTO.convertToDTO(socio.get(), null);
		} else {
			return null;			
		}
    }

    @Override
    public List<SocioDTO> listaAllSocios() {
        List<Socio> lista = socioRepository.findAll();
		List<SocioDTO> listaResultado = new ArrayList<SocioDTO>();
		for (int i = 0; i < lista.size(); ++i) {
		    listaResultado.add(SocioDTO.convertToDTO(lista.get(i), null));
		}
		return listaResultado;
    }

    @Override
    public void deleteSocio(Long id) {
        socioRepository.deleteById(id);
    }
    
}
