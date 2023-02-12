package com.paulacrespo.service;

import java.util.List;

import com.paulacrespo.dto.VideoclubDTO;

public interface VideoclubService {

    VideoclubDTO saveVideoclub(VideoclubDTO VideoclubDTO);
    VideoclubDTO getVideoclubById(Long id);
    VideoclubDTO getVideoclubByNombre(String nombre);
    VideoclubDTO getVideoclubByNombreDireccion(String nombre, String direccion);
    List<VideoclubDTO> listaAllVideoclubs();
    void deleteVideoclub(Long id);
    
}
