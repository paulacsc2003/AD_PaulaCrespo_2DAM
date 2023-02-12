package com.paulacrespo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulacrespo.dto.VideoclubDTO;
import com.paulacrespo.model.Videoclub;
import com.paulacrespo.repository.VideoclubRepository;

@Service
public class VideoclubServiceImpl implements VideoclubService {

    @Autowired
    private VideoclubRepository videoclubRepository;

    @PersistenceContext
	private EntityManager entityManager;

    @Override
    public VideoclubDTO saveVideoclub(VideoclubDTO vcDTO) {
        Videoclub vc = VideoclubDTO.convertToEntity(vcDTO);
        Videoclub newVC = videoclubRepository.save(vc);
        return VideoclubDTO.convertToDTO(newVC);
    }

    @Override
    public VideoclubDTO getVideoclubById(Long id) {

        Optional<Videoclub> vc = videoclubRepository.findById(id);
        if (vc.isPresent()) {
            return VideoclubDTO.convertToDTO(vc.get());
        } else {
            return null;
        }
    }

    @Override
    public VideoclubDTO getVideoclubByNombre(String nombre) {
        Optional<Videoclub> vc = videoclubRepository.findByNombre(nombre);
        if (vc.isPresent()) {
            return VideoclubDTO.convertToDTO(vc.get());
        } else {
            return null;
        }
    }

    @Override
    public VideoclubDTO getVideoclubByNombreDireccion(String nombre, String direccion) {
        Optional<Videoclub> vc = videoclubRepository.findByNombreDireccion(nombre, direccion);
        if (vc.isPresent()) {
            return VideoclubDTO.convertToDTO(vc.get());
        } else {
            return null;
        }
    }

    @Override
    public List<VideoclubDTO> listaAllVideoclubs() {
        List<Videoclub> lista = videoclubRepository.findAll();
		List<VideoclubDTO> listaResultado = new ArrayList<VideoclubDTO>();
		for (int i = 0; i < lista.size(); ++i) {
		    listaResultado.add(VideoclubDTO.convertToDTO(lista.get(i)));
		}
		return listaResultado;
    }

    @Override
    public void deleteVideoclub(Long id) {
        videoclubRepository.deleteById(id);
    }
    
}
