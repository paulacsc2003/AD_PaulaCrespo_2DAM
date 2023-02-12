package com.paulacrespo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulacrespo.model.Videoclub;

public interface VideoclubRepository extends JpaRepository<Videoclub, Long>{
    
    Optional<Videoclub> findByNombre(String nombre);
    Optional<Videoclub> findByNombreDireccion(String nombre, String direccion);
}
