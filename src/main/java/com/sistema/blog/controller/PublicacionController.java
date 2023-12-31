package com.sistema.blog.controller;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.service.PublicacionServicio;
import com.sistema.blog.utilities.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionServicio publicacionServicio;


    @GetMapping
    public PublicacionRespuesta listarPublicaciones(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroDePagina,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false)int medidaDePagina,
                                                    @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return publicacionServicio.obtenerTodasLasPublicaciones(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }

    @GetMapping("/{id}")   
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(publicacionServicio.obtenerPublicacionPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO) {
        return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") long id) {
        PublicacionDTO publicacionRespuesta = publicacionServicio.actualizarPublicacion(publicacionDTO, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id) {
        publicacionServicio.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicación eliminada con éxito", HttpStatus.OK);
    }


}
