package com.paulacrespo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paulacrespo.model.Pelicula;

public interface PeliculaRepository  extends JpaRepository<Pelicula, Long>{
    
    Optional<Pelicula> findByTitulo(String titulo);
    Optional<Pelicula> findByAñoLanzamiento(int añoLanzamiento);
}
