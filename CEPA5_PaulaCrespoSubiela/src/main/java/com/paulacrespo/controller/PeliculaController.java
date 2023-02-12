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
import com.paulacrespo.dto.PeliculaDTO;
import com.paulacrespo.service.PeliculaService;
import com.paulacrespo.service.SocioService;

@RestController
public class PeliculaController {

    private static final Logger myLog = LoggerFactory.getLogger(Application.class);

    @Autowired
    private HttpServletRequest context;

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private SocioService socioService;

    @GetMapping("/peliculas")
    public ResponseEntity<List<PeliculaDTO>> listPeliculas() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        List<PeliculaDTO> lasPeliculas = peliculaService.listaAllPeliculas();
        if (lasPeliculas == null || lasPeliculas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lasPeliculas, HttpStatus.OK);
        }
    }

    @GetMapping("/peliculas/{idPelicula}")
    public ResponseEntity<PeliculaDTO> showPeliculaById(@PathVariable Long idPelicula) {
        myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
        PeliculaDTO laPelicula = peliculaService.getPeliculaById(idPelicula);
        if (laPelicula == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(laPelicula, HttpStatus.OK);
    }

    @PostMapping("/peliculas")
	public ResponseEntity<PeliculaDTO> addPelicula(@RequestBody PeliculaDTO newPelicula) {	
    	myLog.info(context.getMethod() + context.getRequestURI()); 
    	PeliculaDTO laPelicula = peliculaService.savePelicula(newPelicula);
    	 if (laPelicula==null)
         	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         else
         	return new ResponseEntity<>(laPelicula, HttpStatus.OK);
	}

    @PutMapping("/peliculas")
	public ResponseEntity<PeliculaDTO> updatePelicula(@RequestBody PeliculaDTO updPelicula) {	
    	myLog.info(context.getMethod() + context.getRequestURI());
    	PeliculaDTO laPelicula= peliculaService.getPeliculaById(updPelicula.getId());
    	 if (laPelicula==null)
          	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          else {
        	  // como ya sabemos que existe, save actualiza
        	  PeliculaDTO laPeliculaUPD= peliculaService.savePelicula(updPelicula);
        	  return new ResponseEntity<>(laPeliculaUPD,HttpStatus.OK);
          }
	}

    @PostMapping(value="/prova",consumes={"application/json"})
	public void prova(@RequestBody PeliculaDTO newPelicula) {	
    	myLog.info(context.getMethod() + context.getRequestURI()); 
    	myLog.info(newPelicula.toString());
	}

    @DeleteMapping("/peliculas/{idPelicula}")
    public ResponseEntity<String> deletePelicula(@PathVariable Long idPelicula){
    	peliculaService.deletePelicula(idPelicula);
    	return new ResponseEntity<>("Pelicula borrada satisfactoriamenet",HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleError(MethodArgumentTypeMismatchException e) {
    	myLog.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getMessage());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

}
