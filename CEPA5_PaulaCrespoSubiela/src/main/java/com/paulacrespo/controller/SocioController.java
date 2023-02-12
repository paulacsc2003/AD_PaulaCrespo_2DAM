package com.paulacrespo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.paulacrespo.Application;
import com.paulacrespo.dto.SocioDTO;
import com.paulacrespo.service.PeliculaService;
import com.paulacrespo.service.SocioService;


@RestController
public class SocioController {
    private static final Logger myLog = LoggerFactory.getLogger(Application.class);

    @Autowired
    private HttpServletRequest context;

    @Autowired
    private SocioService socioService;

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/socios")
    public ResponseEntity<List<SocioDTO>> listSocios() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        List<SocioDTO> losSocios = socioService.listaAllSocios();
        if (losSocios == null || losSocios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(losSocios, HttpStatus.OK);
        }
    }

    @GetMapping("/socios/{idSocio}")
    public ResponseEntity<SocioDTO> showSocioById(@PathVariable Long idSocio) {
        myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
        SocioDTO elSocio = socioService.getSocioById(idSocio);
        if (elSocio == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(elSocio, HttpStatus.OK);
    }

    @PostMapping("/socios")
	public ResponseEntity<SocioDTO> addPelicula(@RequestBody SocioDTO newSocio) {	
    	myLog.info(context.getMethod() + context.getRequestURI()); 
    	SocioDTO elSocio = socioService.saveSocio(newSocio);
    	 if (elSocio==null)
         	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         else
         	return new ResponseEntity<>(elSocio, HttpStatus.OK);
	}

    @PutMapping("/socios")
	public ResponseEntity<SocioDTO> updatePelicula(@RequestBody SocioDTO updSocio) {	
    	myLog.info(context.getMethod() + context.getRequestURI());
    	SocioDTO elSocio= socioService.getSocioById(updSocio.getIdSocio());
    	 if (elSocio==null)
          	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          else {
        	  // como ya sabemos que existe, save actualiza
        	  SocioDTO elSocioUPD= socioService.saveSocio(updSocio);
        	  return new ResponseEntity<>(elSocioUPD,HttpStatus.OK);
          }
	}

    @PostMapping(value="/prova",consumes={"application/json"})
	public void prova(@RequestBody SocioDTO newSocio) {	
    	myLog.info(context.getMethod() + context.getRequestURI()); 
    	myLog.info(newSocio.toString());
	}

    @DeleteMapping("/socios/{idSocio}")
    public ResponseEntity<String> deletePelicula(@PathVariable Long idSocio){
    	socioService.deleteSocio(idSocio);
    	return new ResponseEntity<>("Socio borrado satisfactoriamenet",HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleError(MethodArgumentTypeMismatchException e) {
    	myLog.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getMessage());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
}
