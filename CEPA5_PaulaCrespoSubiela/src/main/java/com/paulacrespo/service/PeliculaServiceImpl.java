package com.paulacrespo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulacrespo.dto.PeliculaDTO;
import com.paulacrespo.model.Pelicula;
import com.paulacrespo.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl implements PeliculaService {
    
    @Autowired
    private PeliculaRepository peliculaRepository;

    @PersistenceContext
	private EntityManager entityManager;

    @Override
    public PeliculaDTO savePelicula(PeliculaDTO peliculaDTO){

        Pelicula pelicula = PeliculaDTO.convertToEntity(peliculaDTO, null);
        Pelicula newPelicula = peliculaRepository.save(pelicula);
        return PeliculaDTO.convertToDTO(newPelicula, null);

    }

    @Override
    public PeliculaDTO getPeliculaById(Long id) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(id);
        if (pelicula.isPresent()) {
            return PeliculaDTO.convertToDTO(pelicula.get(), null);
        } else {
			return null;			
		}
    }

    @Override
    public PeliculaDTO getPeliculaByTitulo(String titulo) {
        Optional<Pelicula> pelicula = peliculaRepository.findByTitulo(titulo);
        if (pelicula.isPresent()) {
            return PeliculaDTO.convertToDTO(pelicula.get(), null);
        } else {
			return null;			
		}
    }

    @Override
    public PeliculaDTO getPeliculaByAñoLanzamiento(int añoLanzamiento) {
        Optional<Pelicula> pelicula = peliculaRepository.findByAñoLanzamiento(añoLanzamiento);
        if (pelicula.isPresent()) {
            return PeliculaDTO.convertToDTO(pelicula.get(), null);
        } else {
			return null;			
		}
    }

    @Override
    public List<PeliculaDTO> listaAllPeliculas() {
        
        List<Pelicula> lista = peliculaRepository.findAll();
        List<PeliculaDTO> listaResultado = new ArrayList<PeliculaDTO>();
        
        for (int i = 0; i < lista.size(); ++i) {
            listaResultado.add(PeliculaDTO.convertToDTO(lista.get(i), null));
        }

        return listaResultado;
    }

    @Override
    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }

}
