package com.sistema.blog.service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.entitys.Publicacion;


public interface PublicacionServicio {
    
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    
    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir); 
    
    public PublicacionDTO obtenerPublicacionPorId(Long id);
    
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);
    
    public void eliminarPublicacion(long id);
    
}
