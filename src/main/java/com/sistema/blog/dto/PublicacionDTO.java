package com.sistema.blog.dto;

import com.sistema.blog.entitys.Comentario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class PublicacionDTO {
    
   
    @Getter @Setter
    private Long id;
    
    @NotEmpty
    @Size(min = 2, message = "El titulo de la publicación deberia tener almenos 2 carácteres")
    @Getter @Setter
    private String titulo;

    @NotEmpty
    @Size(min = 2, message = "La descripción la publicación deberia tener almenos 2 carácteres")
    @Getter @Setter
    private String descripcion;

    @Getter @Setter
    private String contenido;

    @Getter @Setter
    private Set<Comentario> comentarios;

    public PublicacionDTO() {
        super();
    } 
    
}
