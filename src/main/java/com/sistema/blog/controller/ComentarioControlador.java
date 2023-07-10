package com.sistema.blog.controller;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.service.ComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class ComentarioControlador {
    
    @Autowired
    private ComentarioServicio comentarioServicio;
    
    @GetMapping ("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> listarComentarioPorPublicacionId(@PathVariable(value = "publicacionId") long publicacionId){
        return comentarioServicio.obtenerComentariosPorPublicacionId(publicacionId);
                
    }

    @GetMapping ("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(@PathVariable(value = "publicacionId") Long publicacionId,@PathVariable(value = "id") long comentarioId){
        
        ComentarioDTO comentarioDTO = comentarioServicio.obtenerComentarioPorId(publicacionId, comentarioId);
        return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
    }
    
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(value = "publicacionId") long publicacionId,@Valid @RequestBody ComentarioDTO comentarioDTO) {
        
        return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDTO), HttpStatus.CREATED);
        
    }

    @PutMapping("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(@PathVariable(value = "publicacionId") long publicacionId, @PathVariable (value = "id") Long comentarioId,@Valid  @RequestBody ComentarioDTO comentarioDTO){
        ComentarioDTO ComentarioActualizado = comentarioServicio.actualizarComentario(publicacionId, comentarioId, comentarioDTO);
        return new ResponseEntity<>(ComentarioActualizado, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "id") long comentarioId) {
        
        comentarioServicio.eliminarComentario(publicacionId, comentarioId);
        
        return new ResponseEntity<>("Comentario eliminado con exito", HttpStatus.OK);
        
        
        
    }
    
    
    
}
