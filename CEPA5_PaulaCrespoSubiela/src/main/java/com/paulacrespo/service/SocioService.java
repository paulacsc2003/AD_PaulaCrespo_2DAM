package com.paulacrespo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.paulacrespo.dto.SocioDTO;

@Transactional
public interface SocioService {

    SocioDTO saveSocio(SocioDTO SocioDTO);
    SocioDTO getSocioById(Long id);
    SocioDTO getSocioByNombre(String nombre);
    SocioDTO getSocioByNombreDNI(String nombre, String dni);
    List<SocioDTO> listaAllSocios();
    void deleteSocio(Long id);
    
}
