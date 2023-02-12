package com.paulacrespo.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.paulacrespo.Application;
import com.paulacrespo.dto.VideoclubDTO;
import com.paulacrespo.service.SocioService;
import com.paulacrespo.service.VideoclubService;

public class VideoclubController {
    private static final Logger myLog = LoggerFactory.getLogger(Application.class);

    @Autowired
    private HttpServletRequest context;

    @Autowired
    private VideoclubService videoclubService;

    @Autowired
    private SocioService socioService;

    @GetMapping("/videoclubs")
    public ResponseEntity<List<VideoclubDTO>> listVideoclubs() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        List<VideoclubDTO> losVideoclubs = videoclubService.listaAllVideoclubs();
        if (losVideoclubs == null || losVideoclubs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(losVideoclubs, HttpStatus.OK);
        }
    }

    @GetMapping("/videoclubs/{idVideoclub}")
    public ResponseEntity<VideoclubDTO> showVideoclubById(@PathVariable Long idVideoclub) {
        myLog.info(context.getMethod() + context.getRequestURI() + " from " + context.getRemoteHost());
        VideoclubDTO elVideoclub = videoclubService.getVideoclubById(idVideoclub);
        if (elVideoclub == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(elVideoclub, HttpStatus.OK);
    }

    @PostMapping("/videoclubs")
	public ResponseEntity<VideoclubDTO> addVideoclub(@RequestBody VideoclubDTO newVideoclub) {	
    	myLog.info(context.getMethod() + context.getRequestURI()); 
    	VideoclubDTO elVideoclub = videoclubService.saveVideoclub(newVideoclub);
    	 if (elVideoclub==null)
         	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         else
         	return new ResponseEntity<>(elVideoclub, HttpStatus.OK);
	}

    @PutMapping("/videoclubs")
	public ResponseEntity<VideoclubDTO> updateVideoclub(@RequestBody VideoclubDTO updVideoclub) {	
    	myLog.info(context.getMethod() + context.getRequestURI());
    	VideoclubDTO elVideoclub = videoclubService.getVideoclubById(updVideoclub.getIdVideoclub());
    	 if (elVideoclub==null)
          	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          else {
        	  // como ya sabemos que existe, save actualiza
        	  VideoclubDTO elVideoclubUPD= videoclubService.saveVideoclub(updVideoclub);
        	  return new ResponseEntity<>(elVideoclubUPD,HttpStatus.OK);
          }
	}

    @PostMapping(value="/prova",consumes={"application/json"})
	public void prova(@RequestBody VideoclubDTO newVideoclub) {	
    	myLog.info(context.getMethod() + context.getRequestURI()); 
    	myLog.info(newVideoclub.toString());
	}

    @DeleteMapping("/videoclubs/{idVideoclub}")
    public ResponseEntity<String> deleteVideoclub(@PathVariable Long idVideoclub){
    	videoclubService.deleteVideoclub(idVideoclub);
    	return new ResponseEntity<>("Videoclub borrado satisfactoriamenet",HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleError(MethodArgumentTypeMismatchException e) {
    	myLog.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getMessage());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
}
