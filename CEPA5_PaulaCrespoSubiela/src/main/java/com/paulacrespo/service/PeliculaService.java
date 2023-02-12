package com.paulacrespo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.paulacrespo.dto.PeliculaDTO;

@Transactional
public interface PeliculaService {
    
    PeliculaDTO savePelicula(PeliculaDTO peliculaDTO);
    PeliculaDTO getPeliculaById(Long id);
    PeliculaDTO getPeliculaByTitulo(String titulo);
    PeliculaDTO getPeliculaByAñoLanzamiento(int añoLanzamiento);
    List<PeliculaDTO> listaAllPeliculas();
    void deletePelicula(Long id);
    
}
