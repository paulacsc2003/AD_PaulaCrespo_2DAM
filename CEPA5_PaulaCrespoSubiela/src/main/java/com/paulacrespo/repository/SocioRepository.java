package com.paulacrespo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulacrespo.model.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long>{
    
    Optional<Socio> findByNombre(String nombre);
    Optional<Socio> findByNombreDNI(String nombre, String dni);
}
