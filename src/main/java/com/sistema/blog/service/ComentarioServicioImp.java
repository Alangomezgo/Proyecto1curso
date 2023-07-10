package com.sistema.blog.service;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.entitys.Comentario;
import com.sistema.blog.entitys.Publicacion;
import com.sistema.blog.exceptions.BlogAppException;
import com.sistema.blog.exceptions.ResourceNotFoundException;
import com.sistema.blog.repository.ComentarioRepositorio;
import com.sistema.blog.repository.PublicacionRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServicioImp implements ComentarioServicio{
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    
    @Autowired 
    private PublicacionRepositorio publicacionRepositorio;
    

    @Override
    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
        
        Comentario comentario = mapearEntidad(comentarioDTO);
        Publicacion publicacion = publicacionRepositorio.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", "publicacionId"));
        
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepositorio.save(comentario);
        
        return mapearDTO(nuevoComentario);
        
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId) {
        
       List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(publicacionId);
       
       return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList()); 
    
        
    }

    @Override
    public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId) {

        Publicacion publicacion = publicacionRepositorio.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", "publicacionId"));
        Comentario comentario = comentarioRepositorio.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", "comentarioId"));
        
        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
            
        }
        
        return mapearDTO(comentario);
        
    }

    @Override
    public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudDeComentario) {

        Publicacion publicacion = publicacionRepositorio.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", "publicacionId"));
        Comentario comentario = comentarioRepositorio.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", "comentarioId"));

        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");

        }
        
        comentario.setNombre(solicitudDeComentario.getNombre());
        comentario.setEmail(solicitudDeComentario.getEmail());
        comentario.setCuerpo(solicitudDeComentario.getCuerpo());
        
        Comentario comentarioActualizado = comentarioRepositorio.save(comentario);
        
        return mapearDTO(comentarioActualizado);
        
    }

    @Override
    public void eliminarComentario(Long publicacionId, Long comentarioId) {

        Publicacion publicacion = publicacionRepositorio.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", "publicacionId"));
        Comentario comentario = comentarioRepositorio.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", "comentarioId"));

        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");

        }
        
        comentarioRepositorio.delete(comentario);
        
    }

    private ComentarioDTO mapearDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
        return comentarioDTO;

    }

    private Comentario mapearEntidad(ComentarioDTO comentarioDTO) {
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
        return comentario;

    }
    
    
    

}
